package com.epam.ws.dao.impl;

import java.io.File;
import java.util.List;

import com.epam.ws.dao.CurrencyDAO;
import com.epam.ws.model.Currency;
import com.epam.ws.xml.parser.CurrencySAXParser;

public class CurrencyDAOImpl implements CurrencyDAO {

	private static final String CURRENCY_XML_FILE_NAME = "currencies.xml";
	private CurrencySAXParser parser;
	private File fileWithCurrencies;
	private List<Currency> currencies;

	public CurrencyDAOImpl() {
		fileWithCurrencies = new File(CURRENCY_XML_FILE_NAME);
		parser = new CurrencySAXParser(fileWithCurrencies);
		currencies = parser.getAll();
	}

	public Currency getByAlias(String alias) {
		return currencies.stream().filter(c -> c.getAlias().equalsIgnoreCase(alias)).findFirst().get();
	}

	public List<Currency> getAll() {
		return currencies;
	}

}
