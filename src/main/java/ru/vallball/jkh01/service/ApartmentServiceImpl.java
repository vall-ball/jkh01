package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.ApartmentRepository;
import ru.vallball.jkh01.repository.HouseRepository;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

	static final Logger logger = LoggerFactory.getLogger(ApartmentService.class);

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	HouseRepository houseRepository;

	@Autowired
	MyHardApartmentValidator validator;

	@Override
	public void save(Apartment apartment) throws Exception {
		if (!validator.checkEntrance(apartment)) {
			throw new Exception("The number of the entrance must be not more than the quantity of entrances in the house");
		}
		if (!validator.checkLevel(apartment)) {
			throw new Exception("The number of the level must be not more than the max level in the house");
		}
		if (!validator.isUnique(apartment)) {
			throw new Exception("The apartment with the same number exists in this house"); 
		}
		apartmentRepository.save(apartment);
	}

	@Override
	public List<Apartment> list() {
		return apartmentRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		apartmentRepository.deleteById(id);

	}

	@Override
	public Apartment findById(Long id) {
		return apartmentRepository.findById(id).get();
	}

	@Override
	public List<Apartment> listByHome(String street, String number) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		return apartmentRepository.findByHouse(house);
	}

}
