package com.epam.orm.service;

import java.io.Serializable;

import com.epam.orm.dao.GenericDao;

public interface GenericService<T, I extends Serializable> extends GenericDao<T, I> {
	

}
