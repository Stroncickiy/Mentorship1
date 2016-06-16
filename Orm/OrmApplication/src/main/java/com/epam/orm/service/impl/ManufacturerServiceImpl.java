package com.epam.orm.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.orm.dao.CarDAO;
import com.epam.orm.dao.GenericDao;
import com.epam.orm.dao.ManufacturerDAO;
import com.epam.orm.model.Car;
import com.epam.orm.model.Manufacturer;
import com.epam.orm.service.ManufacurerService;

@Service
public class ManufacturerServiceImpl extends GenericServiceImpl<Manufacturer, Long> implements ManufacurerService {
	@Autowired
	protected ManufacturerDAO dao;

	@Autowired
	private CarDAO carDao;

	@PostConstruct
	public void initDatabase() {
		Manufacturer bmw = new Manufacturer();
		bmw.setName("BMW");

		Manufacturer mercedes = new Manufacturer();
		mercedes.setName("Mercedes");

		Manufacturer maseratti = new Manufacturer();
		maseratti.setName("Maseratti");

		dao.save(bmw);
		dao.save(mercedes);
		dao.save(maseratti);

		Car bmw500 = new Car();
		bmw500.setManufacturer(bmw);
		bmw500.setModel("500");
		bmw500.setOwner(null);
		bmw500.setType("SPORT");
		carDao.save(bmw500);

		Car mercedesG400 = new Car();
		mercedesG400.setManufacturer(mercedes);
		mercedesG400.setModel("G400");
		mercedesG400.setOwner(null);
		mercedesG400.setType("SEDAN");
		carDao.save(mercedesG400);

		Car maserattiY600 = new Car();
		maserattiY600.setManufacturer(maseratti);
		maserattiY600.setModel("Y-600");
		maserattiY600.setOwner(null);
		maserattiY600.setType("SPORT");
		carDao.save(maserattiY600);

	}

	@Override
	protected GenericDao<Manufacturer, Long> getDao() {
		return dao;
	}
}
