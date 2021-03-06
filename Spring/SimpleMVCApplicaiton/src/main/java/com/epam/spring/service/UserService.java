package com.epam.spring.service;

import com.epam.spring.model.User;

import java.util.List;


public interface UserService {

    User register(User user);

    void remove(long id);

    User getById(long id);

    List<User> getAll();

    void update(User user);


    void processAllUsers();

    void removeAllProcessedByDateAndTime();


    void addUserToDbScheduled();

    void longMethod();

	User getUserByEmail(String email);
}

