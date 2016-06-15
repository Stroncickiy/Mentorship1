package com.epam.orm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.AuthTokenDAO;
import com.epam.orm.model.AuthorizationToken;
import com.epam.orm.service.AuthTokenService;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

	@Autowired
	private AuthTokenDAO authTokenDao;

	@Override
	public AuthorizationToken find(Long id) {
		return authTokenDao.find(id);
	}

	@Override
	public void delete(AuthorizationToken obj) {
		authTokenDao.delete(obj);

	}

	@Override
	public void save(AuthorizationToken obj) {
		authTokenDao.save(obj);

	}

	@Override
	public List<AuthorizationToken> getAll() {
		return authTokenDao.getAll();
	}

	@Override
	public void update(AuthorizationToken obj) {
		authTokenDao.update(obj);

	}

}
