package com.epam.spring.service;

import java.util.List;

import com.epam.spring.model.Transaction;

public interface TransactionService {

	Transaction registerTransaction(Transaction transaction);

	List<Transaction> getAll();

	List<Transaction> getForUser(Long userId);

}
