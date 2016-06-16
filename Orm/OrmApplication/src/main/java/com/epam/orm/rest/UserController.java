package com.epam.orm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.orm.model.User;
import com.epam.orm.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public void addUser(User newUser) {
		userService.save(newUser);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAll();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void bulkUpdateUsers(List<User> usersToBeUpdated) {
		userService.bulkUpdate(usersToBeUpdated);

	}

}
