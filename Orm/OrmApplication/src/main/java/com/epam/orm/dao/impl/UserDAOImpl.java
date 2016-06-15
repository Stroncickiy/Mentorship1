package com.epam.orm.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.orm.dao.UserDAO;
import com.epam.orm.model.User;

@Repository
public class UserDAOImpl extends GenericDaoImpl<User, Long> implements UserDAO {

	@Override
	public Class<User> getType() {
		return User.class;
	}

}
