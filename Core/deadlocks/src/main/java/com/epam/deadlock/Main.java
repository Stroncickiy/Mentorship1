package com.epam.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		final Account account1 = new Account(1000);
		final Account account2 = new Account(1000);
		// WITH DEADLOCK
		/*
		 * new Thread(new Runnable() { public void run() { try { new
		 * OperationsWithDeadLock().transfer(account1, account2, 100); } catch
		 * (InsuficentsFundsException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * } }).start(); Thread.sleep(500); new Thread(new Runnable() { public
		 * void run() { try { new OperationsWithDeadLock().transfer(account2,
		 * account1, 100); } catch (InsuficentsFundsException e) {
		 * e.printStackTrace(); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 * 
		 * } }).start();
		 */

		// WITHOUT DEADLOCK

		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.submit(new Runnable() {
			public void run() {
				try {
					new OperationsWithoutDeadLock().transfer(account1, account2, 100);
				} catch (InsuficentsFundsException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		Thread.sleep(500);
		executor.submit(new Runnable() {
			public void run() {
				try {
					new OperationsWithoutDeadLock().transfer(account2, account1, 100);
				} catch (InsuficentsFundsException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		while (executor.awaitTermination(5, TimeUnit.SECONDS))
			;
		System.out.println(account1);
		System.out.println(account2);

	}

}
