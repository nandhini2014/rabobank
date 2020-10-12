package com.rabobank.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Record.
 */
public class Record {

	/** The reference. */
	private String reference;

	/** The mutation. */
	private String mutation;

	/** The end balance. */
	private String endBalance;

	/** The description. */
	private String description;

	/** The account number. */
	private String accountNumber;

	/** The start balance. */
	private String startBalance;

	/**
	 * Instantiates a new record.
	 */
	public Record() {

	}

	/**
	 * Instantiates a new record.
	 *
	 * @param reference     the reference
	 * @param mutation      the mutation
	 * @param endBalance    the end balance
	 * @param description   the description
	 * @param accountNumber the account number
	 * @param startBalance  the start balance
	 */
	public Record(String reference, String mutation, String endBalance, String description, String accountNumber,
			String startBalance) {
		super();
		this.reference = reference;
		this.mutation = mutation;
		this.endBalance = endBalance;
		this.description = description;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	@XmlAttribute(name = "reference")
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the mutation.
	 *
	 * @return the mutation
	 */
	@XmlElement(name = "mutation")
	public String getMutation() {
		return mutation;
	}

	/**
	 * Sets the mutation.
	 *
	 * @param mutation the new mutation
	 */
	public void setMutation(String mutation) {
		this.mutation = mutation;
	}

	/**
	 * Gets the end balance.
	 *
	 * @return the end balance
	 */
	@XmlElement(name = "endBalance")
	public String getEndBalance() {
		return endBalance;
	}

	/**
	 * Sets the end balance.
	 *
	 * @param endBalance the new end balance
	 */
	public void setEndBalance(String endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	@XmlElement(name = "accountNumber")
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the start balance.
	 *
	 * @return the start balance
	 */
	@XmlElement(name = "startBalance")
	public String getStartBalance() {
		return startBalance;
	}

	/**
	 * Sets the start balance.
	 *
	 * @param startBalance the new start balance
	 */
	public void setStartBalance(String startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Record [reference = " + reference + ", mutation = " + mutation + ", endBalance = " + endBalance
				+ ", description = " + description + ", accountNumber = " + accountNumber + ", startBalance = "
				+ startBalance + "]";
	}
}
