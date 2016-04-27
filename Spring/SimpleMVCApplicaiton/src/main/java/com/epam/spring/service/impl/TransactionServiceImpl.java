package com.epam.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.spring.dao.TransactionDAO;
import com.epam.spring.model.Transaction;
import com.epam.spring.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
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
