package com.rabobank.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportsDTO.
 */
public class ReportsDTO {

	/** The error list. */
	List<Report> errorList;

	/**
	 * Gets the error list.
	 *
	 * @return the error list
	 */
	public List<Report> getErrorList() {
		return errorList;
	}

	/**
	 * Sets the error list.
	 *
	 * @param errorList the new error list
	 */
	public void setErrorList(List<Report> errorList) {
		this.errorList = errorList;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ErrorStatementDTO [errorList=" + errorList + "]";
	}

}
