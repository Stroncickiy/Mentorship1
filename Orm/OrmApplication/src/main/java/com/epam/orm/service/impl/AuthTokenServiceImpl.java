package com.epam.orm.service.impl;

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

	@Override
	@Transactional
	public void regenerateAll() {
		// TODO Auto-generated method stub

	}

	@Override
	protected GenericDao<AuthorizationToken, Long> getDao() {
		return dao;
	}

}
