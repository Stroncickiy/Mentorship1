package com.epam.spring.dao;

import java.util.List;

import com.epam.spring.model.Transaction;

public interface TransactionDAO extends CommonDAO<Transaction> {

	List<Transaction> getAllForUser(Long userId);

}
