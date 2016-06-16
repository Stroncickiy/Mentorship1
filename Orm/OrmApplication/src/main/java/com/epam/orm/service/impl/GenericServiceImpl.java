package com.epam.orm.service.impl;

import java.io.Serializable;
import java.util.List;

import com.epam.orm.dao.GenericDao;
import com.epam.orm.service.GenericService;

public abstract class GenericServiceImpl<T, I extends Serializable> implements GenericService<T, I> {

	@Override
	public T find(I id) {
		return getDao().find(id);
	}

	@Override
	public void delete(T obj) {
		getDao().delete(obj);

	}

	@Override
	public void save(T obj) {
		getDao().save(obj);

	}

	@Override
	public List<T> getAll() {
		return getDao().getAll();
	}

	@Override
	public void update(T obj) {
		getDao().update(obj);
	}

	protected abstract GenericDao<T, I> getDao();

}
