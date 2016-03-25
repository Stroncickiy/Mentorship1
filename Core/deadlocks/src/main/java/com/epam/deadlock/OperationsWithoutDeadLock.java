package com.epam.deadlock;

import java.util.concurrent.TimeUnit;

public class OperationsWithoutDeadLock {
	private static final long WAIT_SEC = 3;
	private static int operationsCompleted = 0;
	private int operationId = 0;

	public void transfer(Account fromAccount, Account toAccount, int amount)
			throws InsuficentsFundsException, InterruptedException {
		operationsCompleted += 1;
		operationId = operationsCompleted;
		if (fromAccount.getBalance() < amount) {
			fromAccount.incFailedTransferCount();
			toAccount.incFailedTransferCount();
			throw new InsuficentsFundsException();

		}
		boolean success = false;
		System.out.println("Trying to acquire Lock on fromAccount in operation #" + operationId);
		if (fromAccount.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
			try {
				System.out.println("Lock on fromAccount in operation #" + operationId + " acquired");
				Thread.sleep(1000);
				System.out.println("Trying to acquire Lock on toAccount in operation #" + operationId);
				if (toAccount.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
					try {
						System.out.println("Lock on toAccount in operation #" + operationId + " acquired");
						fromAccount.withdraw(amount);
						toAccount.deposit(amount);
						success = true;
						System.out.println("[WithoutDeadLock] Operation #" + operationId + " successfully completed");
					} finally {
						toAccount.getLock().unlock();
					}
				} else {
					System.out.println("[WithoutDeadLock] Operation #" + operationId + "  failed");
				}
			} finally {
				fromAccount.getLock().unlock();
			}

		} else {
			System.out.println("[WithoutDeadLock] Operation #" + operationId + "  failed");
		}

		if (!success) {
			fromAccount.incFailedTransferCount();
			toAccount.incFailedTransferCount();
		}

	}
}
