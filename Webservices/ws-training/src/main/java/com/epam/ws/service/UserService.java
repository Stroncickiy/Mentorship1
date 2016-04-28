package com.epam.ws.service;

import java.util.List;

import com.epam.ws.model.User;


public interface UserService {

    User register(User user);

    void remove(long id);

    User getById(long id);

    List<User> getAll();

    void update(User user);

	User getUserByEmail(String email);
}

