package com.epam.concurency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

class Race {
	private Random rand = new Random();
	private int distance = rand.nextInt(250);
	private List<String> horses = new ArrayList<String>();

	public Race(String... names) {
		this.horses.addAll(Arrays.asList(names));
	}
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void run() throws InterruptedException {
		System.out.println("And the horses are stepping up to the gate...");
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch finish = new CountDownLatch(horses.size());
		final List<String> places = Collections.synchronizedList(new ArrayList<String>());

		for (final String horse : horses) {
			new Thread(new Runnable() {
				public void run() {
					try {
						System.out.println(horse + " stepping up to the gate...");
						start.await();

						int traveled = 0;
						while (traveled < distance) {

							Thread.sleep(rand.nextInt(3) * 1000);


							traveled += rand.nextInt(15);
							System.out.println(horse + " advanced to " + traveled + "!");
						}
						finish.countDown();
						System.out.println(horse + " crossed the finish!");
						places.add(horse);
					} catch (InterruptedException intEx) {
						System.out.println("ABORTING RACE!!!");
						intEx.printStackTrace();
					}
				}
			}).start();
		}

		System.out.println("And... they're off!");
		start.countDown();

		finish.await();
		System.out.println("And we have our winners!");
		System.out.println(places.get(0) + " took the gold...");
		System.out.println(places.get(1) + " got the silver...");
		System.out.println("and " + places.get(2) + " took home the bronze.");
	}
}

public class CDLApp {
	public static void main(String[] args) throws InterruptedException, java.io.IOException {
		System.out.println("Prepping...");

		Race r = new Race("Beverly Takes a Bath", "RockerHorse", "Phineas", "Ferb", "Tin Cup",
				"I'm Faster Than a Monkey", "Glue Factory Reject");

		System.out.println("It's a race of " + r.getDistance() + " lengths");

		System.out.println("Press Enter to run the race....");
		System.in.read();

		r.run();
	}
}