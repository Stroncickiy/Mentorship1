package com.epam.ws.service.holder;

import com.epam.ws.service.CurrencyService;
import com.epam.ws.service.TransactionService;
import com.epam.ws.service.UserService;
import com.epam.ws.service.impl.CurrencyServiceImpl;
import com.epam.ws.service.impl.TransactionServiceImpl;
import com.epam.ws.service.impl.UserServiceImpl;

public class ServiceFactory {

	private UserService userService;
	private CurrencyService currencyService;
	private TransactionService transactionService;

	private ServiceFactory() {
		userService = new UserServiceImpl();
		currencyService = new CurrencyServiceImpl();
		transactionService = new TransactionServiceImpl();
	}

	private static ServiceFactory instance;

	public synchronized static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}
	
	
	

}
