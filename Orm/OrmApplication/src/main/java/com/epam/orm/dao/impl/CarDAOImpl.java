package com.epam.orm.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.orm.dao.CarDAO;
import com.epam.orm.model.Car;

@Repository
public class CarDAOImpl extends GenericDaoImpl<Car, Long>  implements CarDAO{

	@Override
	public Class<Car> getType() {
		return Car.class;
	}

}
