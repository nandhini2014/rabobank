package com.rabobank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Records.
 */
@XmlRootElement(name = "records")
public class Records {

	/**
	 * Instantiates a new records.
	 */
	public Records() {

	}

	/**
	 * Instantiates a new records.
	 *
	 * @param record the record
	 */
	public Records(List<Record> record) {
		super();
		this.record = record;
	}

	/** The record. */
	private List<Record> record;

	/**
	 * Gets the record.
	 *
	 * @return the record
	 */
	@XmlElement(name = "record")
	public List<Record> getRecord() {
		return record;
	}

	/**
	 * Sets the record.
	 *
	 * @param record the new record
	 */
	public void setRecord(List<Record> record) {
		this.record = record;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Records [record=" + record + "]";
	}

}
