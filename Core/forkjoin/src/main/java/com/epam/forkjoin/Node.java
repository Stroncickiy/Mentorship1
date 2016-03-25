package com.epam.forkjoin;

import java.util.Collection;

public interface Node {
	Collection<Node> getChildren();

	public void addClild(Node node);
	long getValue();
}
