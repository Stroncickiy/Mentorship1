package com.epam.orm.service;

import com.epam.orm.model.AuthorizationToken;

public interface AuthTokenService extends GenericService<AuthorizationToken, Long> {

	void regenerateAll();

}
