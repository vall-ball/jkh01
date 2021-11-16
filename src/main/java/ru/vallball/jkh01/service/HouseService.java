package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.House;

public interface HouseService {
	
	void save(House house);

	List<House> list();

	void delete(Long id);

	House findById(Long id);
	
	House findByAddress(String street, String number);
	
}
