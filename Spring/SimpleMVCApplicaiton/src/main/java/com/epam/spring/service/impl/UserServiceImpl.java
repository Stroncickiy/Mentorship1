package com.epam.spring.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
	private final Random rand = new Random();

	@Autowired
	private UserDAO userDAO;

	public User register(User user) {
		return userDAO.add(user);
	}

	public void remove(long id) {
		userDAO.remove(id);
	}

	public User getById(long id) {
		return userDAO.getById(id);
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Async
	@Override
	public void processAllUsers() {
		userDAO.processNonProcessedUsers();
	}

	@Scheduled(cron = "${processUsersCronExpression}")
	@Override
	public void removeAllProcessedByDateAndTime() {
		userDAO.removeAllProcessed();
	}

	@Scheduled(fixedDelay = 10_000)
	@Override
	public void addUserToDbScheduled() {
		User user = new User();
		user.setProcessed(false);
		user.setLastName(randomIdentifier());
		user.setFirstName(randomIdentifier());
		user.setEmail(randomIdentifier() + "@gmail.com");
		user.setPassword(randomIdentifier());
		user.setEnabled(true);
		user.setBirthday(LocalDate.now());
		userDAO.add(user);
	}

	private String randomIdentifier() {
		StringBuilder builder = new StringBuilder();
		int length = rand.nextInt(5) + 5;
		for (int i = 0; i < length; i++) {
			builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
		}
		return builder.toString();
	}

	@Async
	@Override
	public void longMethod() {
		try {
			Thread.sleep(11_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

}
