package com.epam.orm.service;

import java.util.List;

import com.epam.orm.model.User;

public interface UserService extends GenericService<User, Long> {

	void bulkUpdate(List<User> usersToBeUpdated);
		
	
}
