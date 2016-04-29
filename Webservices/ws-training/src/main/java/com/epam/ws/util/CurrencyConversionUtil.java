package com.epam.ws.util;

import com.epam.ws.model.Currency;

public class CurrencyConversionUtil {

	public static Double convertToDefault(Currency from, Double ammount) {
		return ammount * from.getRatioToMainCurrency();

	}

	public static Double convertFromDefaultTo(Currency to, Double ammout) {
		return ammout / to.getRatioToMainCurrency();
	}

}
