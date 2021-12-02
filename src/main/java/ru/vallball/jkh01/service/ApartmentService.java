package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.Apartment;


public interface ApartmentService {
	
	void save(Apartment apartment) throws Exception;

	List<Apartment> list();

	void delete(Long id);
	
	void deleteByAddress(String street, String numberOfHouse, int numberOfApartment);

	Apartment findById(Long id);
	
	List<Apartment> listByHome(String street, String number);
	
	Apartment findByAddress(String street, String numberOfHouse, int numberOfApartment);
	
	void update(Long id, Apartment apartment) throws Exception;
}
