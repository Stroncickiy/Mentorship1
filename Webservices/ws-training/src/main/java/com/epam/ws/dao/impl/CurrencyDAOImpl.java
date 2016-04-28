package com.epam.ws.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.epam.ws.dao.CurrencyDAO;
import com.epam.ws.model.Currency;
import com.epam.ws.xml.parser.CurrencySAXParser;

public class CurrencyDAOImpl implements CurrencyDAO {

	private static final String CURRENCY_XML_FILE_NAME = "currencies.xml";
	private CurrencySAXParser parser;
	private File fileWithCurrencies;

	@PostConstruct
	public void init() throws IOException {
		fileWithCurrencies = new File(CURRENCY_XML_FILE_NAME);
		parser = new CurrencySAXParser(fileWithCurrencies);
	}

	public Currency getByAlias(String alias) {
		return getAll().stream().filter(c -> c.getAlias().equalsIgnoreCase(alias)).findFirst().get();
	}

	public List<Currency> getAll() {
		return parser.getAll();
	}

}
