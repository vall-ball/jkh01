package ru.vallball.jkh01.service;

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
public class ApartmentServiceImpl implements ApartmentService{
	
	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	HouseRepository houseRepository;

	@Override
	public void save(Apartment apartment) {
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
		return apartmentRepository.getById(id);
	}

	@Override
	public List<Apartment> listByHome(String street, String number) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
		return apartmentRepository.findByHouse(house);
	}

}
