package ru.vallball.jkh01.model;

import java.util.List;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.jkh01.repository.ApartmentRepository;
import ru.vallball.jkh01.repository.HouseRepository;

@Component
public class UniqueApartmentValidator implements ConstraintValidator<UniqueApartmentConstraint, Apartment>{

	@Autowired
	HouseRepository houseRepository;
	
	@Override
	public void initialize(UniqueApartmentConstraint constraint) {
			   org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	public boolean isValid(Apartment value, ConstraintValidatorContext context) {
		House house = houseRepository.findById(value.getHouse().getId()).get();
		boolean answer = true;
		for (Apartment a : house.getApartments()) {
			if (a.getNumber() == value.getNumber()) {
				answer = false;
				break;
			}
		}
		return answer;
	}

}
