package com.epam.spring.service.impl;

import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    private final java.util.Random rand = new java.util.Random();

    private final Set<String> identifiers = new HashSet<>();

    @Autowired
    private UserDAO dao;


    public User register(User user) {
        return dao.add(user);
    }

    public void remove(long id) {
        dao.remove(id);
    }

    public User getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }


    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Async
    @Override
    public void processAllUsers() {
        dao.processNonProcessedUsers();
    }

    @Scheduled(cron = "${processUsersCronExpression}")
    @Override
    public void removeAllProccessedByDateAndTime() {
        dao.removeAllProcessed();
    }

    @Scheduled(fixedDelay = 10_000)
    @Override
    public void addUserToDbScheduled() {
        User user = new User();
        user.setProcessed(false);
        user.setLastName(randomIdentifier());
        user.setFirstName(randomIdentifier());
        user.setBirthday(LocalDate.now());
        dao.add(user);
    }

    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++)
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            if (identifiers.contains(builder.toString()))
                builder = new StringBuilder();
        }
        return builder.toString();
    }

    @Async
    @Override
    public void longMethod() {
        try {
            Thread.sleep(11_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
