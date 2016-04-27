package com.epam.spring.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.epam.spring.app.enums.UserRole;
import com.epam.spring.dao.UserDAO;
import com.epam.spring.model.User;
import com.google.common.base.Splitter;

@Repository("userDaoJdbc")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User add(User user) {
		String query = "INSERT INTO users (firstName,lastName,processed,birthday,email,password,enabled,roles) VALUES (?,?,?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setBoolean(3, user.isProcessed());
			preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setBoolean(7, user.isEnabled());
			preparedStatement.setString(8, StringUtils.collectionToCommaDelimitedString(user.getRoles()));
			return preparedStatement;
		}, holder);
		Long newEventID = holder.getKey().longValue();
		user.setId(newEventID);
		return user;
	}

	@Override
	public void update(User user) {
		String query = "UPDATE  users SET firstName=?,lastName=?,processed=?,birthday=?,email=?,password=?, enabled=?,roles=? WHERE id=?";
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setBoolean(3, user.isProcessed());
			preparedStatement.setDate(4, Date.valueOf(user.getBirthday()));
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setBoolean(7, user.isEnabled());
			preparedStatement.setString(8, StringUtils.collectionToCommaDelimitedString(user.getRoles()));
			preparedStatement.setLong(9, user.getId());
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
		return jdbcTemplate.query(query, (resultSet, i) -> {
			return getUserFromRS(resultSet);
		});
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
		user.setBirthday(resultSet.getDate("birthday").toLocalDate());
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setEnabled(resultSet.getBoolean("enabled"));
		user.setRoles(Splitter.on(",").omitEmptyStrings().trimResults().splitToList(resultSet.getString("roles"))
				.stream().map(UserRole::valueOf).collect(Collectors.toList()));
		return user;
	}

	public void processNonProcessedUsers() {
		String query = "UPDATE  users SET processed=? WHERE processed=?";
		jdbcTemplate.update(connection -> {
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

	@Override
	public User getUserByEmail(String email) {
		String query = "SELECT * FROM users WHERE email=?";
		return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
			return getUserFromRS(resultSet);
		}, email);
	}
}
