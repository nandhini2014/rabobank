package com.rabobank.service.test;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Assert;

import com.rabobank.model.ReportsDTO;
import com.rabobank.service.CustomerStatementService;

@RunWith(SpringJUnit4ClassRunner.class)
class CustomerStatementServiceTest {

	@Mock
	CustomerStatementService customerService = new CustomerStatementService();

	@Test
	void testProcessCSVStatement_ok() throws Exception {
		MockMultipartFile csvFile = new MockMultipartFile("test.csv", "", "text/csv",
				"194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\n194261,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\n194266,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23"
				.getBytes());
		ReportsDTO reportsDTO = customerService.processCustomerStatement(csvFile);
		assertNotNull(reportsDTO);
	}
	
	@Test
	void testProcessCSVStatementDuplicateRecord_ok() throws Exception {
		MockMultipartFile csvFile = new MockMultipartFile("test.csv", "", "text/csv",
				"194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23\n194261,NL27SNSB0917829871,Clothes for Willem Dekker,91.23,+15.57,106.8\n194261,NL91RABO0315273637,Clothes from Jan Bakker,21.6,-41.83,-20.23"
				.getBytes());
		ReportsDTO reportsDTO = customerService.processCustomerStatement(csvFile);
		assertNotNull(reportsDTO);
	}

	@Test
	void testProcessXMLStatement_ok() throws Exception {
		MockMultipartFile xmlFile = new MockMultipartFile("test.xml", "", "text/xml",
				"<records><record reference=\"130498\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Peter Theuß</description><startBalance>26.9</startBalance><mutation>-18.78</mutation><endBalance>8.12</endBalance></record></records>\n"
						.getBytes());
		ReportsDTO reportsDTO = customerService.processCustomerStatement(xmlFile);
		assertNotNull(reportsDTO);

	}
	
	@Test
	void testProcessXMLInvalidEndBalance_ok() throws Exception {
		MockMultipartFile xmlFile = new MockMultipartFile("test.xml", "", "text/xml",
				"<records><record reference=\"130498\"><accountNumber>NL69ABNA0433647324</accountNumber><description>Tickets for Peter Theuß</description><startBalance>27.9</startBalance><mutation>-18.78</mutation><endBalance>8.12</endBalance></record></records>\n"
						.getBytes());
		ReportsDTO reportsDTO = customerService.processCustomerStatement(xmlFile);
		assertNotNull(reportsDTO);

	}
	
	@Test
	void testProcessInvalidFile_fail() throws Exception {
		MockMultipartFile xmlFile = new MockMultipartFile("test.json", "", "application.json","Test".getBytes());
		ReportsDTO reports = null;
		try {
		 reports = customerService.processCustomerStatement(xmlFile);
		} catch(Exception e) {
			assertNull(reports);
		}

	}
	
	

}
