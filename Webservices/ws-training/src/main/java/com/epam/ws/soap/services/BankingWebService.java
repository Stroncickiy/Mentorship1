package com.epam.ws.soap.services;

import javax.xml.ws.Holder;

import com.epam.ws.model.User;

public interface BankingWebService {

	boolean deposit(Double ammount, String currency, Long userId, boolean needConvert,Holder<String> hash);

	boolean withdraw(Double ammount, String currency, Long userId, boolean needConvert,Holder<String> hash);

	Long addUser(User user);

}
