package com.epam.ws.soap.services.impl;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import com.epam.ws.model.Transaction;
import com.epam.ws.model.Transaction.OperationType;
import com.epam.ws.model.User;
import com.epam.ws.service.CurrencyService;
import com.epam.ws.service.TransactionService;
import com.epam.ws.service.UserService;
import com.epam.ws.service.holder.ServiceFactory;
import com.epam.ws.soap.services.BankingWebService;
import com.epam.ws.transaction.TransactionBuilder;
import com.epam.ws.util.HashUtil;

@WebService
@HandlerChain(file="handler-chain.xml")
public class BankingWebServiceImpl  implements BankingWebService{
	
	private UserService userService = ServiceFactory.getInstance().getUserService();
	private CurrencyService currencyService = ServiceFactory.getInstance().getCurrencyService();
	private TransactionService transactionService = ServiceFactory.getInstance().getTransactionService();
	
	@WebMethod
	public 	@WebResult(name="success") boolean deposit(@WebParam(name="ammount")Double ammount, @WebParam(name="currency")String currency, @WebParam(name="userId") Long userId,@WebParam(header=true,name="CC") boolean needConvert,@WebParam(header=true,name="hash",mode=Mode.OUT)Holder<String> responseHash) {
		responseHash.value = HashUtil.getHashFromString(String.valueOf(ammount)+currency+String.valueOf(userId));
		return performTransaction(ammount, currency, userId, OperationType.DEPOSIT,needConvert);
	}

	@WebMethod
	public 	@WebResult(name="success") boolean withdraw(@WebParam(name="ammount")Double ammount, @WebParam(name="currency")String currency, @WebParam(name="userId") Long userId,@WebParam(header=true,name="CC") boolean needConvert,@WebParam(header=true,name="hash",mode=Mode.OUT)Holder<String> responseHash) {
		responseHash.value = HashUtil.getHashFromString(String.valueOf(ammount)+currency+String.valueOf(userId));
		return performTransaction(ammount, currency, userId, OperationType.WITHDRAWAL,needConvert);

	}

	private boolean performTransaction(Double ammount, String currency, Long userId, OperationType operationType,boolean needConvert) {
		return new TransactionBuilder()
				.forUser(userId)
				.withAmmout(ammount)
				.withType(operationType)
				.converted(needConvert)
				.withCurrency(currencyService.getByAlias(currency))
				.perform();
	}

	@WebMethod
	public @WebResult(name="addedUserId") Long addUser(@WebParam(name="newUser")User user) {
		User registerredUser = userService.register(user);
		return registerredUser.getId();
	}
	
	@WebMethod
	public @WebResult(name="transaction") List<Transaction> getTransactionsByUser(@WebParam(name="userId")Long userId){
		List<Transaction> transactionsOfUser = transactionService.getForUser(userId);
		return transactionsOfUser;
		
	}

}
