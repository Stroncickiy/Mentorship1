package com.epam.orm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.CarDAO;
import com.epam.orm.dao.GenericDao;
import com.epam.orm.model.Car;
import com.epam.orm.service.CarService;

@Service
public class CarServiceImpl extends GenericServiceImpl<Car, Long> implements CarService {
	@Autowired
	protected CarDAO dao;

	@Override
	protected GenericDao<Car, Long> getDao() {
		return dao;
	}

}
