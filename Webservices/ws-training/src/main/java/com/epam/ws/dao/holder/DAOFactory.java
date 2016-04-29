package com.epam.ws.dao.holder;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.epam.ws.dao.CurrencyDAO;
import com.epam.ws.dao.TransactionDAO;
import com.epam.ws.dao.UserDAO;
import com.epam.ws.dao.impl.CurrencyDAOImpl;
import com.epam.ws.dao.impl.TransactionDAOImpl;
import com.epam.ws.dao.impl.UserDaoXmlImpl;

public class DAOFactory {
	private UserDAO userDAO;
	private CurrencyDAO currencyDAO;
	private TransactionDAO transactionDAO;
	private static DAOFactory instance;

	public synchronized static DAOFactory getInstance() {
		if (instance == null) {
			try {
				instance = new DAOFactory();
			} catch (Exception e) {
				System.out.println("Failed to init DAO factory " + e.getMessage());
			}
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		if (userDAO == null) {
			try {
				userDAO = new UserDaoXmlImpl();
			} catch (JAXBException | IOException e) {
				throw new RuntimeException("Failed to construct users DAO");
			}
		}
		return userDAO;
	}

	public CurrencyDAO getCurrencyDAO() {
		if (currencyDAO == null) {
			currencyDAO = new CurrencyDAOImpl();
		}
		return currencyDAO;
	}

	public TransactionDAO getTransactionDAO() {
		if (transactionDAO == null) {
			try {
				transactionDAO = new TransactionDAOImpl();
			} catch (ParserConfigurationException | IOException | TransformerException e) {
				throw new RuntimeException("Failed to construct transactions DAO");
			}
		}
		return transactionDAO;
	}

}
