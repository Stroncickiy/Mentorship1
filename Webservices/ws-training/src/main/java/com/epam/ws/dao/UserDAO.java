package com.epam.ws.dao;


import com.epam.ws.model.User;

public interface UserDAO extends CommonDAO<User> {


	User getUserByEmail(String email);


}
