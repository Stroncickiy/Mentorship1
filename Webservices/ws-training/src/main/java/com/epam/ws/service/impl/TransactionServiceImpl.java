package com.epam.ws.service.impl;

import java.util.List;

import com.epam.ws.dao.TransactionDAO;
import com.epam.ws.model.Transaction;
import com.epam.ws.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	private TransactionDAO transactionDAO;

	@Override
	public Transaction registerTransaction(Transaction transaction) {
		return transactionDAO.add(transaction);
	}

	@Override
	public List<Transaction> getAll() {
		return transactionDAO.getAll();
	}

	@Override
	public List<Transaction> getForUser(Long userId) {
		return transactionDAO.getAllForUser(userId);
	}

}
