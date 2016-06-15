package com.epam.orm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.AuthTokenDAO;
import com.epam.orm.dao.UserDAO;
import com.epam.orm.model.AuthorizationToken;
import com.epam.orm.model.User;
import com.epam.orm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AuthTokenDAO authTokenDAO;

	public void save(User user) {
		user.setCars(new ArrayList<>());
		user.setPermissions(new ArrayList<>());
		userDAO.save(user);
		user.setAuthorizationToken(generateAuthToken(user));
		userDAO.save(user);
	}

	private AuthorizationToken generateAuthToken(User user) {
		AuthorizationToken authorizationToken = new AuthorizationToken();
		authorizationToken.setOwner(user);
		authorizationToken.setToken("toker");
		authTokenDAO.save(authorizationToken);
		return authorizationToken;
	}

	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User find(Long id) {
		return userDAO.find(id);
	}

	@Override
	public void delete(User obj) {
		userDAO.delete(obj);

	}

	@Override
	public void update(User obj) {
		userDAO.update(obj);

	}

}
