package com.epam.ws.soap.services;

import com.epam.ws.model.User;

public interface BankingWebService {

	boolean deposit(Double ammount, String currency, Long userId, boolean needConvert);

	boolean withdraw(Double ammount, String currency, Long userId, boolean needConvert);

	Long addUser(User user);

}
