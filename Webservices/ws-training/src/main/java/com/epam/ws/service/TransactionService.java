package com.epam.ws.service;

import java.util.List;

import com.epam.ws.model.Transaction;

public interface TransactionService {

	Transaction registerTransaction(Transaction transaction);

	List<Transaction> getAll();

	List<Transaction> getForUser(Long userId);

}
