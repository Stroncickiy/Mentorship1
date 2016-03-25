package com.epam.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static int nodesCount = 0;
	public static Random random = new Random();

	public static void main(String[] args) {

		Node root = new NodeImpl(1);
		Node leftChild = new NodeImpl(random.nextInt(20000));
		Node rightChild = new NodeImpl(random.nextInt(20000));
		root.addClild(leftChild);
		root.addClild(rightChild);
		generateTree(root);
		Long summ = new ForkJoinPool().invoke(new ValueSumCounter(root));
		System.out.println("summ from tree node is " + summ);
	}

	public static void generateTree(Node parent) {
		if (nodesCount <= 1000) {
			Node leftChild = new NodeImpl(random.nextInt(20000));
			Node rightChild = new NodeImpl(random.nextInt(20000));
			generateChildsForNode(leftChild);
			generateChildsForNode(rightChild);
			generateTree(leftChild);
			generateTree(rightChild);
		} else {
			System.out.println("tree builded");
		}
	}

	public static void generateChildsForNode(Node parent) {
		Node leftChild = new NodeImpl(random.nextInt(20000));
		Node rightChild = new NodeImpl(random.nextInt(20000));
		parent.addClild(leftChild);
		parent.addClild(rightChild);
		nodesCount += 2;
	}

}
