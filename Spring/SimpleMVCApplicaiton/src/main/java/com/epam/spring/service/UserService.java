package com.epam.spring.service;

import com.epam.spring.model.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Long register(User user);

    Boolean remove(long id);

    User getById(long id);

    List<User> getAll();

    Boolean isUserExist(User user);

    Boolean update(User user);


    Boolean processAllUsers();

    void removeAllProccessedByDateAndTime();


    void addUserToDbScheduled();

    void longMethod() throws InterruptedException;
}

