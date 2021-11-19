package ru.vallball.jkh01.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.repository.HouseRepository;
import ru.vallball.jkh01.service.HouseService;

@Component
public class ApartmentValidator implements ConstraintValidator<ApartmentConstraint, Apartment> {

	@Autowired
	HouseRepository houseRepository;

	@Override
	public void initialize(ApartmentConstraint constraint) {
		org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public boolean isValid(Apartment value, ConstraintValidatorContext context) {
		House house = houseRepository.findById(value.getHouse().getId()).get();

		if (value.getEntrance() > house.getEntrances() || value.getLevel() > house.getLevels()) {
			return false;
		} else {
			return true;
		}
	}

}
