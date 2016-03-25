package com.epam.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


@Component
public abstract class AbstractDAO<O> implements Serializable {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public abstract Long add(O item);

    public abstract boolean update(O item);

    public abstract boolean remove(Long key);

    public abstract List<O> getAll();

    public abstract O getById(Long key);



}
