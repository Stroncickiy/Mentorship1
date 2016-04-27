package com.epam.spring.service;

import com.epam.spring.model.Currency;

public interface CurrencyService {

	Currency getByAlias(String alias);

}
