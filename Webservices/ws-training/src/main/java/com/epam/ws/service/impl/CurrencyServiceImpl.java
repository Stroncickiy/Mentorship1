package com.epam.ws.service.impl;

import com.epam.ws.dao.CurrencyDAO;
import com.epam.ws.model.Currency;
import com.epam.ws.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyDAO currencyDao;

	@Override
	public Currency getByAlias(String alias) {
		return currencyDao.getByAlias(alias);
	}

}
