package com.epam.orm.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, I extends Serializable> {
	public T find(I id);

	public void delete(T obj);

	public void save(T obj);

	public List<T> getAll();
	
	public void update(T obj);
}