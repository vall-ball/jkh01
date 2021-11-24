package ru.vallball.jkh01.service;

import org.springframework.beans.factory.annotation.Autowired;
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
}
