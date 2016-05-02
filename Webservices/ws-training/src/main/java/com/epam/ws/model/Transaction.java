
package com.epam.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {

	public enum OperationType {
		WITHDRAWAL, DEPOSIT
	}

	private Long id;
	private Long userId;
	private OperationType operationType;
	private Double ammount;
	private Currency currency;
	private boolean convertedToDefaultCurrency;
	private Double amountInDefaultCurrency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getAmountInDefaultCurrency() {
		return amountInDefaultCurrency;
	}

	public void setAmountInDefaultCurrency(Double amountInDefaultCurrency) {
		this.amountInDefaultCurrency = amountInDefaultCurrency;
	}

	public boolean isConvertedToDefaultCurrency() {
		return convertedToDefaultCurrency;
	}

	public void setConvertedToDefaultCurrency(boolean convertedToDefaultCurrency) {
		this.convertedToDefaultCurrency = convertedToDefaultCurrency;
	}

}
