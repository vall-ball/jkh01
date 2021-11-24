package ru.vallball.jkh01.service;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

import ru.vallball.jkh01.model.Apartment;


public interface ApartmentService {
	
	void save(Apartment apartment) throws Exception;

	List<Apartment> list();

	void delete(Long id);

	Apartment findById(Long id);
	
	List<Apartment> listByHome(String street, String number);
}
