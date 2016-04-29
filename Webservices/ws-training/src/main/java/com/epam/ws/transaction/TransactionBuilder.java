package com.epam.ws.transaction;

import com.epam.ws.model.Currency;
import com.epam.ws.model.Transaction;
import com.epam.ws.service.TransactionService;
import com.epam.ws.service.holder.ServiceFactory;

public class TransactionBuilder {

	private static TransactionService  transactionService = ServiceFactory.getInstance().getTransactionService();
	
	private Transaction transaction = new Transaction();

	public TransactionBuilder withType(Transaction.OperationType operationType) {
		transaction.setOperationType(operationType);
		return this;
	}

	public TransactionBuilder withAmmout(Double ammount) {
		transaction.setAmmount(ammount);
		return this;
	}

	public TransactionBuilder withCurrency(Currency currency) {
		transaction.setCurrency(currency);
		return this;
	}

	public TransactionBuilder converted(boolean isConversionNeeded) {
		transaction.setConvertedToDefaultCurrency(isConversionNeeded);
		return this;
	}

	public TransactionBuilder forUser(Long userId) {
		transaction.setUserId(userId);
		return this;
	}

	public boolean perform() {
		return transactionService.registerTransaction(transaction).getId()!=null;

	}

}
