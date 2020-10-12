package com.rabobank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.model.ReportsDTO;
import com.rabobank.service.CustomerStatementService;

/**
 * The Class CustomerStatementController.
 *
 * @author Nandhini - Controller that binds the REST request uri to the
 *         respective handler methods.
 */
@RestController
@RequestMapping("/rabobank")
public class CustomerStatementController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CustomerStatementController.class);

	/** The customer service. */
	@Autowired
	CustomerStatementService customerService;

	/**
	 * Generate report.
	 *
	 * @param file the file
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/generateReport", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> generateReport(@RequestParam("file") MultipartFile file) throws Exception {
		logger.info("Inside generate Report: {} : getContentType: {}", file.getOriginalFilename(),
				file.getContentType());
		ReportsDTO errorStatementList = customerService.processCustomerStatement(file);

		return new ResponseEntity<Object>(errorStatementList, HttpStatus.OK);
	}

}
