package com.epam.spring.service.impl;

import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private  final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    private final java.util.Random rand = new java.util.Random();

   private final Set<String> identifiers = new HashSet<>();

    @Autowired
    private UserDAO userDAO;


    public Long register(User user) {
        return userDAO.add(user);
    }

    public Boolean remove(long id) {
        return userDAO.remove(id);
    }

    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public Boolean isUserExist(User user) {
        return userDAO.isUserExists(user);
    }

    @Override
    public Boolean update(User user) {
        return userDAO.update(user);
    }

    @Async
    @Override
    public Boolean processAllUsers() {
        return userDAO.processNonProcessedUsers();
    }

    @Scheduled(cron = "0 0 * *  * Mon")
    @Override
    public void removeAllProccessedByDateAndTime() {
        userDAO.remoAllProcessed();
    }

    @Scheduled(fixedDelay = 10_000)
    @Override
    public void addUserToDbScheduled() {
        User user = new User();
        user.setProcessed(false);
        user.setLastName(randomIdentifier());
        user.setFirstName(randomIdentifier());
        userDAO.add(user);
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
    public void longMethod() throws InterruptedException {
        Thread.sleep(11_000);
    }

}
