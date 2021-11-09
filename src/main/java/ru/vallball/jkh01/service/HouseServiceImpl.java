package ru.vallball.jkh01.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired
	HouseRepository houseRepository;
	
	@Autowired
	ApartmentRepository apartmentRepository;

	@Override
	public void save(House house) {
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
		return houseRepository.getById(id);
	}

}
