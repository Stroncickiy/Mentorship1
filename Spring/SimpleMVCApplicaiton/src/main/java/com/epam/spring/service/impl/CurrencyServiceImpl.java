package com.epam.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.spring.dao.CurrencyDAO;
import com.epam.spring.model.Currency;
import com.epam.spring.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDAO currencyDao;


	@Override
	public Currency getByAlias(String alias) {
		return currencyDao.getByAlias(alias);
	}

	

}
