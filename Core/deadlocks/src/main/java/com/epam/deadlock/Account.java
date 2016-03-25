package com.epam.deadlock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private int balance;
	private Lock lock;
	private AtomicInteger failedTransfers;

	public Account(int initialBalance) {
		this.balance = initialBalance;
		lock = new ReentrantLock();
		failedTransfers = new AtomicInteger(0);
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public void incFailedTransferCount() {
		failedTransfers.incrementAndGet();
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", failedTransfers=" + failedTransfers + "]";
	}

}
