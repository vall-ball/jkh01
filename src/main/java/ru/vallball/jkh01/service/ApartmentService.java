package ru.vallball.jkh01.service;

import java.util.List;

import ru.vallball.jkh01.model.Apartment;


public interface ApartmentService {
	
	void save(Apartment apartment);

	List<Apartment> list();

	void delete(Long id);

	Apartment findById(Long id);
	
	List<Apartment> listByHome(String street, String number);
}
