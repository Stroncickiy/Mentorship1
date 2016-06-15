package com.epam.orm.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.orm.dao.AuthTokenDAO;
import com.epam.orm.model.AuthorizationToken;

@Repository
public class AuthTokenDAOImpl extends GenericDaoImpl<AuthorizationToken, Long> implements AuthTokenDAO {

	@Override
	public Class<AuthorizationToken> getType() {
		return AuthorizationToken.class;
	}

}
