package com.epam.spring.dao;

import com.epam.spring.model.User;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserDAO extends AbstractDAO<User> {

    @Override
    public Long add(User item) {
        String query = "INSERT INTO users (firstName,lastName,processed) VALUES (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setBoolean(3, item.isProcessed());
            return preparedStatement;
        }, holder);
        Long newEventID = holder.getKey().longValue();
        return newEventID;
    }

    @Override
    public boolean update(User item) {
        String query = "UPDATE  users SET firstName=?,lastName=?,processed=? WHERE id=?";
        int updated = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setBoolean(3, item.isProcessed());
            preparedStatement.setLong(4, item.getId());
            return preparedStatement;
        });
        return updated > 0;
    }

    @Override
    public boolean remove(Long key) {
        String query = "DELETE FROM users  WHERE id=? ";
        int removed = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
        return removed > 0;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users ";
        List<User> userstList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        });
        return userstList;
    }


    public Integer getNumberOfNotProcessed() {
        String query = "SELECT COUNT(*) FROM users WHERE processed = ? ";
        Integer numberOfNonProcessed = 0;
        List<Integer> query1 = jdbcTemplate.query(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setBoolean(1, false);
            }
        }, (resultSet, i) -> {
            return resultSet.getInt(1);
        });
        numberOfNonProcessed = query1.get(0);
        return numberOfNonProcessed;
    }


    @Override
    public User getById(Long key) {
        String query = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        }, key);
    }

    private User getUserFromRS(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLastName(resultSet.getString("lastName"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setProcessed(resultSet.getBoolean("processed"));
        return user;
    }


    public boolean isUserExists(User user) {
        String query = "SELECT COUNT(*) FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return resultSet.getInt(1) > 0;
        }, user.getId());
    }

    public boolean processNonProcessedUsers() {
        String query = "UPDATE  users SET processed=? WHERE processed=?";
        int updated = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setBoolean(2, false);
            return preparedStatement;
        });
        return updated > 0;

    }

    public boolean remoAllProcessed() {
        String query = "DELETE FROM users  WHERE processed=? ";
        int removed = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            return preparedStatement;
        });
        return removed > 0;
    }
}
