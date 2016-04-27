package com.epam.spring.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.epam.spring.dao.CurrencyDAO;
import com.epam.spring.model.Currency;
import com.epam.spring.parser.CurrencySAXParser;

@Repository
public class CurrencyDAOImpl implements CurrencyDAO {

	private CurrencySAXParser parser;
	private File fileWithCurrencies;

	@Autowired
	private Environment environment;

	@PostConstruct
	public void init() throws IOException {
		fileWithCurrencies = new File(environment.getProperty("currencies.xml.file.name"));
		parser = new CurrencySAXParser(fileWithCurrencies);
	}

	public Currency getByAlias(String alias) {
		return getAll().stream().filter(c -> c.getAlias().equalsIgnoreCase(alias)).findFirst().get();
	}

	public List<Currency> getAll() {
		return parser.getAll();
	}

}
