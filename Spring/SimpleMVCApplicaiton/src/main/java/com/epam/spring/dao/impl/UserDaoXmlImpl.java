package com.epam.spring.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import com.epam.spring.model.UsersList;
import com.epam.spring.util.JaxbProcessor;

@Repository("userDaoXml")
public class UserDaoXmlImpl implements UserDAO {

	@Autowired
	private Environment environment;

	private File usersXmlFile;

	private JaxbProcessor<UsersList> jaxbProcessor;

	@PostConstruct
	private void init() throws JAXBException, IOException {
		usersXmlFile = new File(environment.getProperty("users.xml.file.name"));
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
	public void processNonProcessedUsers() {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		dataFromFile.stream().filter(u -> u.isProcessed() == false).forEach(u -> u.setProcessed(true));
		jaxbProcessor.writeToFile();
	}

	@Override
	public void removeAllProcessed() {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		dataFromFile.removeIf(u -> u.isProcessed());
		jaxbProcessor.writeToFile();

	}

	@Override
	public User getUserByEmail(String email) {
		List<User> dataFromFile = jaxbProcessor.getDataFromFile().getUsers();
		User user = dataFromFile.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
		return user;

	}

}
