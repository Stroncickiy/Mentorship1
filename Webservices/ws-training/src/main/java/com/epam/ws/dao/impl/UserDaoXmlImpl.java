package com.epam.ws.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import com.epam.ws.dao.UserDAO;
import com.epam.ws.model.User;
import com.epam.ws.model.UsersList;
import com.epam.ws.xml.processor.JaxbProcessor;


public class UserDaoXmlImpl implements UserDAO {


	private static final String USERS_FILE_NAME = "users.xml";

	private File usersXmlFile;

	private JaxbProcessor<UsersList> jaxbProcessor;

	@PostConstruct
	private void init() throws JAXBException, IOException {
		usersXmlFile = new File(USERS_FILE_NAME);
		jaxbProcessor = new JaxbProcessor<UsersList>()
				.forClass(UsersList.class)
				.from(usersXmlFile)
				.withInitialData(new UsersList());

	}

	@Override
	public User add(User item) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		item.setId((long) (dataFromFile.size() + 1));
		dataFromFile.add(item);
		jaxbProcessor.writeToFile();
		return item;
	}

	@Override
	public void update(User item) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		dataFromFile.removeIf(u -> u.getId().equals(item.getId()));
		dataFromFile.add(item);
		jaxbProcessor.writeToFile();

	}

	@Override
	public void remove(Long key) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		dataFromFile.removeIf(u -> u.getId().equals(key));
		jaxbProcessor.writeToFile();
	}

	@Override
	public List<User> getAll() {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		return dataFromFile;
	}

	@Override
	public User getById(Long key) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		User user = dataFromFile.stream().filter(u -> u.getId().equals(key)).findFirst().get();
		return user;
	}





	@Override
	public User getUserByEmail(String email) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		User user = dataFromFile.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
		return user;

	}

}
