package com.epam.ws.dao;


import java.util.List;


public interface CommonDAO<O> {

    O add(O item);

    void update(O item);

    void remove(Long key);

    List<O> getAll();

    O getById(Long key);
}
