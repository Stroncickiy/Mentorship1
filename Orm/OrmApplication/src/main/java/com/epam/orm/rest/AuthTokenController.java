package com.epam.orm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.orm.service.AuthTokenService;

@RestController
@RequestMapping(path = "token")
public class AuthTokenController {

	@Autowired
	private AuthTokenService tokenService;

	@RequestMapping(path = "/regenerate", method = RequestMethod.GET)
	public void regenerateAllTokens() {
		tokenService.regenerateAll();
	}
}
