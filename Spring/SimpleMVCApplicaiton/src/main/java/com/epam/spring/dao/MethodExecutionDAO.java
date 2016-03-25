package com.epam.spring.dao;

import com.epam.spring.model.User;
import com.epam.spring.model.MethodExecutionRecord;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Repository
public class MethodExecutionDAO extends AbstractDAO<MethodExecutionRecord> {

    @Override
    public Long add(MethodExecutionRecord item) {
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
        return newEventID;
    }

    @Override
    public boolean update(MethodExecutionRecord item) {
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
        return updated > 0;
    }

    @Override
    public boolean remove(Long key) {
        String query = "DELETE FROM executions  WHERE id=? ";
        int removed = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
        return removed > 0;
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
            preparedStatement.setBoolean(1,true);
        }, (resultSet, i) -> {
            return getExecutionsFromRS(resultSet);
        });
        return executionList;
    }
}
