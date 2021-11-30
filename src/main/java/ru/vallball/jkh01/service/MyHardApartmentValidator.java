package ru.vallball.jkh01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.HouseRepository;

@Component
public class MyHardApartmentValidator {

	@Autowired
	HouseRepository houseRepository;

	public boolean checkEntrance(Apartment value) {
		House house = houseRepository.getById(value.getHouse().getId());
		if (value.getEntrance() > house.getEntrances()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkLevel(Apartment value) {
		House house = houseRepository.getById(value.getHouse().getId());
		if (value.getLevel() > house.getLevels()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isUnique(Apartment value) {
		boolean answer = true;
		House house = houseRepository.getById(value.getHouse().getId());
		for (Apartment a : house.getApartments()) {
			if (a.getNumber() == value.getNumber() && (value.getId() == null || !value.getId().equals(a.getId()))) {
				answer = false;
				break;
			}
		}
		return answer;
	}

}
