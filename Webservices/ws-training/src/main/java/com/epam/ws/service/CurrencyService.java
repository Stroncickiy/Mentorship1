package com.epam.ws.service;

import com.epam.ws.model.Currency;

public interface CurrencyService {

	Currency getByAlias(String alias);

}
