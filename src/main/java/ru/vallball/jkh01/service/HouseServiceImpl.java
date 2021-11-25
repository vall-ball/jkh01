package ru.vallball.jkh01.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.ApartmentRepository;
import ru.vallball.jkh01.repository.HouseRepository;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	static final Logger logger = LoggerFactory.getLogger(HouseService.class);

	@Autowired
	HouseRepository houseRepository;

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	MyHardHouseValidator validator;

	@Override
	public void save(House house) throws Exception {
		house.setStreet(house.getStreet().toLowerCase());
		house.setNumber(house.getNumber().toLowerCase());
		if (!validator.isUnique(house)) {
			throw new Exception("The number and the street must be unique");
		}
		houseRepository.save(house);
		for (Apartment a : house.getApartments()) {
			apartmentRepository.save(a);
		}
	}

	@Override
	public List<House> list() {
		return houseRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		houseRepository.deleteById(id);
	}

	@Override
	public House findById(Long id) {
		return houseRepository.findById(id).get();
	}

	@Override
	public House findByAddress(String street, String number) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		if (house == null) {
			throw new NoSuchElementException();
		}
		return houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
	}

	@Override
	public List<House> listByStreet(String street) {
		return houseRepository.findByStreetIgnoreCase(street);
	}

	@Override
	public void deleteByAddress(String street, String number) {
		long l = houseRepository.deleteByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		if (l == 0) {
			throw new EmptyResultDataAccessException((int) l);
		}
	}

}
