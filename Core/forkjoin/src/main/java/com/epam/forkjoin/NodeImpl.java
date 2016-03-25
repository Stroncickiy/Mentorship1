package com.epam.forkjoin;

import java.util.ArrayList;
import java.util.Collection;

public class NodeImpl implements Node {

	private long value;
	private ArrayList<Node> clildrens;

	public NodeImpl(int value) {
		this.value = value;
		clildrens = new ArrayList<Node>();
	}

	public void addClild(Node node) {
		clildrens.add(node);
	}

	public Collection<Node> getChildren() {
		return clildrens;
	}

	public long getValue() {
		return value;
	}

}
