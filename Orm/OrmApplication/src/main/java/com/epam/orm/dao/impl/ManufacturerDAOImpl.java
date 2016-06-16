package com.epam.orm.dao.impl;

import org.springframework.stereotype.Repository;

import com.epam.orm.dao.ManufacturerDAO;
import com.epam.orm.model.Manufacturer;

@Repository
public class ManufacturerDAOImpl extends GenericDaoImpl<Manufacturer, Long> implements ManufacturerDAO {

	@Override
	public Class<Manufacturer> getType() {
		return Manufacturer.class;
	}

}
