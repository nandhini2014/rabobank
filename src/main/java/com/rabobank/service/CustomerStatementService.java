package com.rabobank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileTypeInvalidException;
import com.rabobank.model.Record;
import com.rabobank.model.Records;
import com.rabobank.model.Report;
import com.rabobank.model.ReportsDTO;

/**
 * The Class CustomerStatementService.
 */
@Service
public class CustomerStatementService {

	/** The Constant TYPE_CSV. */
	public static final String TYPE_CSV = "text/csv";

	/** The Constant TYPE_XML. */
	public static final String TYPE_XML = "text/xml";

	/** The Constant logger. */
	public static final Logger logger = LoggerFactory.getLogger(CustomerStatementService.class);

	/**
	 * Process customer statement.
	 *
	 * @param file the file
	 * @return the reports DTO
	 * @throws Exception the exception
	 */
	public ReportsDTO processCustomerStatement(MultipartFile file) throws Exception {

		String fileContent = file.getContentType();
		validateFileContent(fileContent);
		Records statementList = null;
		if (TYPE_CSV.equalsIgnoreCase(fileContent)) {
			statementList = convertCSVToDTO(file);

		} else {
			statementList = convertXMLToDTO(file);
		}
		logger.info("Statement List :  {}", statementList);
		List<Report> filteredList = generateReport(statementList.getRecord());
		ReportsDTO reportsDTO = new ReportsDTO();
		reportsDTO.setErrorList(filteredList);

		logger.info("Statement List : report - {}", reportsDTO);
		return reportsDTO;

	}

	/**
	 * Convert XML to DTO.
	 *
	 * @param file the file
	 * @return the records
	 * @throws IOException   Signals that an I/O exception has occurred.
	 * @throws JAXBException the JAXB exception
	 */
	private Records convertXMLToDTO(MultipartFile file) throws IOException, JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Records records = (Records) unmarshaller.unmarshal(file.getInputStream());
		return records;
	}

	/**
	 * Generate report.
	 *
	 * @param statementList the statement list
	 * @return the list
	 */
	private List<Report> generateReport(List<Record> statementList) {
		List<Report> duplicateRecordList = extractDuplicateRecords(statementList);
		List<Report> invalidRecordList = validateRecords(statementList);
		duplicateRecordList.addAll(invalidRecordList);
		List<Report> reportList = duplicateRecordList.stream().distinct().collect(Collectors.toList());
		return reportList;

	}

	/**
	 * Extract duplicate records.
	 *
	 * @param statementList the statement list
	 * @return the list
	 */
	private List<Report> extractDuplicateRecords(List<Record> statementList) {

		List<Report> reportList = new ArrayList<>();

		Map<String, List<Record>> statementByReferenceNo = statementList.stream()
				.collect(Collectors.groupingBy(Record::getReference));

		for (Entry<String, List<Record>> entry : statementByReferenceNo.entrySet()) {

			List<Record> entryValueList = entry.getValue();
			if (entryValueList != null && entryValueList.size() > 1) {

				for (Record rec : entryValueList) {
					Report report = new Report();
					report.setDescription(rec.getDescription());
					report.setTransactionNumber(rec.getReference());
					reportList.add(report);
				}
			}
		}
		return reportList;
	}

	/**
	 * Validate records.
	 *
	 * @param statementList the statement list
	 * @return the list
	 */
	private List<Report> validateRecords(List<Record> statementList) {
		List<Report> reportList = new ArrayList<>();
		for (Record statement : statementList) {
			Double sum = Double.valueOf(statement.getStartBalance()) + Double.valueOf(statement.getMutation());
			String formatValue = String.format("%.2f", sum);
			String endBalance = String.format("%.2f", Double.valueOf(statement.getEndBalance()));
			logger.info("transNumber = {}, Sum = {} :: {}", statement.getReference(), sum, formatValue);
			if (!formatValue.equals(endBalance)) {
				Report report = new Report();
				report.setDescription(statement.getDescription());
				report.setTransactionNumber(statement.getReference());
				reportList.add(report);
			}
		}
		return reportList;
	}

	/**
	 * Convert CSV to DTO.
	 *
	 * @param file the file
	 * @return the records
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Records convertCSVToDTO(MultipartFile file) throws IOException {
		InputStreamReader isr = new InputStreamReader(file.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String str;
		Records statementList = new Records();
		List<Record> recordList = new ArrayList<>();
		br.readLine();
		while ((str = br.readLine()) != null) {
			String record[] = str.split(",");
			Record statement = new Record();
			statement.setReference(record[0]);
			statement.setAccountNumber(record[1]);
			statement.setDescription(record[2]);
			statement.setStartBalance(record[3]);
			statement.setMutation(record[4]);
			statement.setEndBalance(record[5]);
			recordList.add(statement);
		}
		statementList.setRecord(recordList);
		return statementList;
	}

	/**
	 * Validate file content.
	 *
	 * @param fileContent the file content
	 */
	private static void validateFileContent(String fileContent) {

		boolean valid = false;

		if (TYPE_CSV.equals(fileContent) || TYPE_XML.equals(fileContent)) {
			valid = true;
		}

		if (!valid) {
			throw new FileTypeInvalidException();
		}

	}

}
