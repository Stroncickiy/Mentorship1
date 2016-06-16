package com.epam.orm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.AuthTokenDAO;
import com.epam.orm.dao.GenericDao;
import com.epam.orm.dao.UserDAO;
import com.epam.orm.model.AuthorizationToken;
import com.epam.orm.model.User;
import com.epam.orm.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserDAO dao;

	@Autowired
	private AuthTokenDAO authTokenDAO;

	public void save(User user) {
		user.setCars(new ArrayList<>());
		user.setPermissions(new ArrayList<>());
		dao.save(user);
		user.setAuthorizationToken(generateAuthToken(user));
		dao.save(user);
	}

	private AuthorizationToken generateAuthToken(User user) {
		AuthorizationToken authorizationToken = new AuthorizationToken();
		authorizationToken.setOwner(user);
		authorizationToken.setToken("toker");
		authTokenDAO.save(authorizationToken);
		return authorizationToken;
	}

	@Transactional // ?
	@Override
	public void bulkUpdate(List<User> usersToBeUpdated) {
		// TODO do update for all users

	}

	@Override
	protected GenericDao<User, Long> getDao() {
		return dao;
	}

}
