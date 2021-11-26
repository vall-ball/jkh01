package ru.vallball.jkh01.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.HashMapChangeSet;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.HouseRepository;

@Component
public class MyHardHouseValidator {

	@Autowired
	HouseRepository houseRepository;
	
	public boolean isUnique(House value) {
		boolean answer = true;
		for (House h : houseRepository.findAll()) {
			if (h.getNumber().equals(value.getNumber()) && h.getStreet().equals(value.getStreet())) {
				answer = false;
				break;
			}
		}
		return answer;
	}
	
	public boolean isFieldsChanged(Long id, Map<String, Object> changes) {
		House house = houseRepository.getById(id);
		if (changes.containsKey("levels") && (int) changes.get("levels") != house.getLevels()) {
			return true;
		}
		if (changes.containsKey("entrances") && (int) changes.get("entrances") != house.getEntrances()) {
			return true;
		}
		if (changes.containsKey("apartmentsByLevel") && (int) changes.get("apartmentsByLevel") != house.getApartmentsByLevel()) {
			return true;
		}
		return false;
	}
}
