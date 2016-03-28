package com.epam.spring.dao.impl;

import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User add(User user) {

        String query = "INSERT INTO users (firstName,lastName,processed) VALUES (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setBoolean(3, user.isProcessed());
            return preparedStatement;
        }, holder);
        Long newEventID = holder.getKey().longValue();
        user.setId(newEventID);
        return user;
    }

    @Override
    public void update(User user) {
        String query = "UPDATE  users SET firstName=?,lastName=?,processed=? WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setBoolean(3, user.isProcessed());
            preparedStatement.setLong(4, user.getId());
            return preparedStatement;
        });
    }

    @Override
    public void remove(Long id) {
        String query = "DELETE FROM users  WHERE id=? ";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users ";
        List<User> userstList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        });
        return userstList;
    }



    @Override
    public User getById(Long id) {
        String query = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        }, id);
    }

    private User getUserFromRS(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLastName(resultSet.getString("lastName"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setProcessed(resultSet.getBoolean("processed"));
        return user;
    }




    public void processNonProcessedUsers() {
        String query = "UPDATE  users SET processed=? WHERE processed=?";
        int updated = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setBoolean(2, false);
            return preparedStatement;
        });
    }

    public void removeAllProcessed() {
        String query = "DELETE FROM users  WHERE processed=? ";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            return preparedStatement;
        });
    }
}

