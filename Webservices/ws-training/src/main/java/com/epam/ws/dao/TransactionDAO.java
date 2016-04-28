package com.epam.ws.dao;

import java.util.List;

import com.epam.ws.model.Transaction;

public interface TransactionDAO extends CommonDAO<Transaction> {

	List<Transaction> getAllForUser(Long userId);

}
