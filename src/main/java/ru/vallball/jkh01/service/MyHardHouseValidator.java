package ru.vallball.jkh01.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.HouseRepository;

@Component
public class MyHardHouseValidator {

	@Autowired
	HouseRepository houseRepository;

	static final Logger logger = LoggerFactory.getLogger(HouseService.class);

	public boolean isUnique(House value) {
		List<House> houses = houseRepository.findAll();
		for (House h : houses) {
			if (h.getNumber().equals(value.getNumber()) && h.getStreet().equals(value.getStreet())
					&& (value.getId() == null || !value.getId().equals(h.getId()))) {
				return false;
			}
		}
		return true;
	}

	public boolean isUnique(Long id, Map<String, Object> changes) {
		if (!changes.containsKey("number") && !changes.containsKey("street")) {
			return true;
		}
		
		List<House> houses = houseRepository.findAll();
		House house = houseRepository.getById(id);
		
		if (changes.containsKey("number") && changes.containsKey("street")) {
			for (House h : houses) {
				if (h.getNumber().equals(((String) changes.get("number")).toLowerCase()) && h.getStreet().equals(((String) changes.get("street")).toLowerCase())) {
					return false;
				}
			}
		} else if (changes.containsKey("number") && !changes.containsKey("street")) {
			for (House h : houses) {
				if (h.getNumber().equals(((String) changes.get("number")).toLowerCase()) && h.getStreet().equals(house.getStreet())) {
					return false;
				}
			}
		} else if (!changes.containsKey("number") && changes.containsKey("street")) {
			for (House h : houses) {
				if (h.getNumber().equals(house.getNumber()) && h.getStreet().equals(((String) changes.get("street")).toLowerCase())) {
					return false;
				}
			}
		}
		return true;
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

	public boolean isFieldsChangedByAddress(String street, String number, Map<String, Object> changes) {
		House house = houseRepository.findByStreetIgnoreCaseAndNumberIgnoreCase(street, number);
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
	
	public boolean isFieldsNotChanged(Long id, House house) {
		House houseForUpdate = houseRepository.getById(id);
		return houseForUpdate.getApartmentsByLevel() == house.getApartmentsByLevel() && houseForUpdate.getEntrances() == house.getEntrances() && houseForUpdate.getLevels() == house.getLevels();
	}
}
