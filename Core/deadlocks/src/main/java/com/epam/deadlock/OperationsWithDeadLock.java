package com.epam.deadlock;

public class OperationsWithDeadLock {
	private static int operationsCompleted = 0;
	private int operationId = 0;

	public void transfer(Account fromAccount, Account toAccount, int amount)
			throws InsuficentsFundsException, InterruptedException {
		operationsCompleted += 1;
		operationId = operationsCompleted;
		if (fromAccount.getBalance() < amount) {
			throw new InsuficentsFundsException();

		}
		System.out.println("Trying to acquire Lock on fromAccount in operation #" + operationId);
		synchronized (fromAccount) {
			System.out.println("Lock on fromAccount in operation #" + operationId + " acquired");
			Thread.sleep(1000);
			System.out.println("Trying to acquire Lock on toAccount in operation #" + operationId);
			synchronized (toAccount) {
				System.out.println("Lock on toAccount in operation #" + operationId + " acquired");
				fromAccount.withdraw(amount);
				toAccount.deposit(amount);
                System.out.println("[WithDeadLock] Operation #"+operationId+" successfully completed");
			}
		}

	}
}
