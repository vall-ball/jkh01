package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Map;

import ru.vallball.jkh01.model.House;

public interface HouseService {
	
	void save(House house) throws Exception;
	
	public void update(Long id, Map<String, Object> changes) throws Exception;
	
	public void update(String street, String number, Map<String, Object> changes) throws Exception;

	List<House> list();

	void delete(Long id);
	
	void deleteByAddress(String street, String number);

	House findById(Long id);
	
	House findByAddress(String street, String number);
	
	List<House> listByStreet(String street);

	void update(Long id, House house) throws Exception;
	
	void update(String street, String number, House house) throws Exception;
	
}
