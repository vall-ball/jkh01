package ru.vallball.jkh01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public void save(House house) {
		logger.info(house.toString());
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
		return houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
	}

}
