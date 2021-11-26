package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.House;

public interface HouseService {
	
	void save(House house) throws Exception;
	
	void update(House house, boolean check) throws Exception;

	List<House> list();

	void delete(Long id);
	
	void deleteByAddress(String street, String number);

	House findById(Long id);
	
	House findByAddress(String street, String number);
	
	List<House> listByStreet(String street);
	
}
