package com.epam.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ValueSumCounter extends RecursiveTask<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Node node;

	public ValueSumCounter(Node node) {
		this.node = node;
	}

	@Override
	protected Long compute() {
		long sum = node.getValue();
		List<ValueSumCounter> subTasks = new LinkedList<ValueSumCounter>();

		for (Node child : node.getChildren()) {
			ValueSumCounter task = new ValueSumCounter(child);
			task.fork();
			subTasks.add(task);
		}

		for (ValueSumCounter task : subTasks) {
			sum += task.join();
		}

		return sum;
	}

}