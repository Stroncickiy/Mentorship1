package com.epam.spring.dao;

import com.epam.spring.model.Currency;

public interface CurrencyDAO{

	Currency getByAlias(String alias);

}
