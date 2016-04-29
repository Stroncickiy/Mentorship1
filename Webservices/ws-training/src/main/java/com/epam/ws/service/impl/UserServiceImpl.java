package com.epam.ws.service.impl;

import java.util.List;

import com.epam.ws.dao.UserDAO;
import com.epam.ws.dao.holder.DAOFactory;
import com.epam.ws.model.User;
import com.epam.ws.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl() {
		userDAO = DAOFactory.getInstance().getUserDAO();
	}

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

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}

}
