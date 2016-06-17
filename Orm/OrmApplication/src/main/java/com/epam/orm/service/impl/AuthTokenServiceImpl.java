package com.epam.orm.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.AuthTokenDAO;
import com.epam.orm.dao.GenericDao;
import com.epam.orm.model.AuthorizationToken;
import com.epam.orm.service.AuthTokenService;

@Service
public class AuthTokenServiceImpl extends GenericServiceImpl<AuthorizationToken, Long> implements AuthTokenService {

	@Autowired
	protected AuthTokenDAO dao;
	
	private SecureRandom random = new SecureRandom();

	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}

	@Override
	@Transactional
	public void regenerateAll() {
		List<AuthorizationToken> all = getAll();
		for (AuthorizationToken authorizationToken : all) {
			authorizationToken.setToken(nextSessionId());
			dao.save(authorizationToken);
		}
	}

	@Override
	protected GenericDao<AuthorizationToken, Long> getDao() {
		return dao;
	}

}
