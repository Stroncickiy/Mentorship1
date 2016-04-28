package com.epam.ws.dao;

import com.epam.ws.model.Currency;

public interface CurrencyDAO{

	Currency getByAlias(String alias);

}
