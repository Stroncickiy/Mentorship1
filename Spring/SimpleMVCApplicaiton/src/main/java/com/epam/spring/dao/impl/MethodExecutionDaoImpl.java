package com.epam.spring.dao.impl;


import com.epam.spring.dao.MethodExecutionDAO;
import com.epam.spring.model.MethodExecutionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Duration;
import java.util.List;

@Repository
public class MethodExecutionDAOImpl implements MethodExecutionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public MethodExecutionRecord add(MethodExecutionRecord item) {
        String query = "INSERT INTO executions (method_name,duration,execution_started,is_long) VALUES (?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getMethodName());
            preparedStatement.setLong(2, item.getDuration().toMillis());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getExecuted()));
            preparedStatement.setBoolean(4, item.isPermittedDurationExceeded());
            return preparedStatement;
        }, holder);
        Long newEventID = holder.getKey().longValue();
        item.setId(newEventID);
        return item;
    }

    @Override
    public void update(MethodExecutionRecord item) {
        String query = "UPDATE  executions SET method_name=?,duration=?,execution_started=?,is_long=? WHERE id=?";
        int updated = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getMethodName());
            preparedStatement.setLong(2, item.getDuration().toMillis());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getExecuted()));
            preparedStatement.setBoolean(4, item.isPermittedDurationExceeded());
            preparedStatement.setLong(5, item.getId());
            return preparedStatement;
        });
    }

    @Override
    public void remove(Long key) {
        String query = "DELETE FROM executions  WHERE id=? ";
        int removed = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<MethodExecutionRecord> getAll() {
        String query = "SELECT * FROM executions ";
        List<MethodExecutionRecord> executionList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getExecutionsFromRS(resultSet);
        });
        return executionList;
    }

    private MethodExecutionRecord getExecutionsFromRS(ResultSet resultSet) throws SQLException {
        MethodExecutionRecord methodExecutionRecord = new MethodExecutionRecord();
        methodExecutionRecord.setId(resultSet.getLong("id"));
        methodExecutionRecord.setDuration(Duration.ofMillis(resultSet.getLong("duration")));
        methodExecutionRecord.setExecuted(resultSet.getTimestamp("execution_started").toLocalDateTime());
        methodExecutionRecord.setMethodName(resultSet.getString("method_name"));
        methodExecutionRecord.setPermittedDurationExceeded(resultSet.getBoolean("is_long"));
        return methodExecutionRecord;
    }


    @Override
    public MethodExecutionRecord getById(Long key) {
        String query = "SELECT * FROM executions WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getExecutionsFromRS(resultSet);
        }, key);
    }


    public List<MethodExecutionRecord> getLongRunningMethods() {
        String query = "SELECT * FROM executions WHERE is_long=? ";
        List<MethodExecutionRecord> executionList = jdbcTemplate.query(query, preparedStatement -> {
            preparedStatement.setBoolean(1, true);
        }, (resultSet, i) -> {
            return getExecutionsFromRS(resultSet);
        });
        return executionList;
    }
}
