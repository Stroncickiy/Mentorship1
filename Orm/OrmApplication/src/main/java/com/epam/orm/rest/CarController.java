package com.epam.orm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.orm.model.Car;
import com.epam.orm.service.CarService;

@RestController
@RequestMapping(path = "car")
public class CarController {

	@Autowired
	private CarService carService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Car> getAllCars() {
		return carService.getAll();
	}

}
