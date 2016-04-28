package com.epam.ws.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.ws.model.Currency;

public class CurrencySAXParser {

	private SAXParserFactory factory;
	private SAXParser saxParser;
	private File fileWithCurrencies;

	public CurrencySAXParser(File fileWithCurrencies) {
		this.fileWithCurrencies = fileWithCurrencies;
		factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	private class CurrencyHandler extends DefaultHandler {
		protected List<Currency> curencyList;

		public CurrencyHandler(List<Currency> curencyList) {
			this.curencyList = curencyList;
		}
	}

	public List<Currency> getAll() {
		List<Currency> resultingCurrencyList = new ArrayList<>();
		try {
			CurrencyHandler handler = new CurrencyHandler(resultingCurrencyList) {

				boolean ratioToMainCurrencyElement = false;
				boolean isMainCurrencyElement = false;

				Currency currentCurrency;

				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {

					if (qName.equalsIgnoreCase("Currency")) {
						currentCurrency = new Currency();
						currentCurrency.setId(Long.valueOf(attributes.getValue("id")));
						currentCurrency.setAlias(attributes.getValue("alias"));
					}

					if (qName.equalsIgnoreCase("ratioToMainCurrency")) {
						ratioToMainCurrencyElement = true;
					}

					if (qName.equalsIgnoreCase("isMainCurrency")) {
						isMainCurrencyElement = true;
					}

				}

				public void endElement(String uri, String localName, String qName) throws SAXException {
					curencyList.add(currentCurrency);

				}

				public void characters(char ch[], int start, int length) throws SAXException {

					if (ratioToMainCurrencyElement) {
						currentCurrency.setRatioToMainCurrency(Float.valueOf(new String(ch, start, length)));
						ratioToMainCurrencyElement = false;
					}
					if (isMainCurrencyElement) {
						currentCurrency.setMainCurrency(Boolean.valueOf(new String(ch, start, length)));
						isMainCurrencyElement = false;
					}
				}

			};
			saxParser.parse(new InputSource(new FileInputStream(fileWithCurrencies)), handler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultingCurrencyList;

	}

}
