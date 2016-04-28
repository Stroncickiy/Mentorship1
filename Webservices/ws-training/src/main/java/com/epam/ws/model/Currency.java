package com.epam.ws.model;

public class Currency {
	private Long id;
	private String alias;
	private float ratioToMainCurrency;
	private boolean isMainCurrency;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public float getRatioToMainCurrency() {
		return ratioToMainCurrency;
	}

	public void setRatioToMainCurrency(float ratioToMainCurrency) {
		this.ratioToMainCurrency = ratioToMainCurrency;
	}

	public boolean isMainCurrency() {
		return isMainCurrency;
	}

	public void setMainCurrency(boolean isMainCurrency) {
		this.isMainCurrency = isMainCurrency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
