package org.vgs.accountswa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "entry")
@NamedQueries({ @NamedQuery(name = "Entry.getAll", query = "SELECT e FROM Entry e") })
public class Entry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3013695847762428440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message="Invalid Transaction Name")
	@NotBlank(message="Invalid Transaction Name")
	@Column(name = "tx_name")
	private String transactionName;

	@NotNull(message="Invalid Transaction Type(eg: CREDIT)")
	@NotBlank(message="Invalid Transaction Type(eg: CREDIT)")
	@Column(name = "tx_type")
	private String transactionType;

	@NotNull(message="Invalid amount")
	@DecimalMin(value="0.0", message="Invalid amount")
	@Column(name = "amount")
	private Double amount;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private Date updatedDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the transactionName
	 */
	public String getTransactionName() {
		return transactionName;
	}

	/**
	 * @param transactionName
	 *            the transactionName to set
	 */
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
