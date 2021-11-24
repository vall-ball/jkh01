package ru.vallball.jkh01.jail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.House;
import ru.vallball.jkh01.repository.HouseRepository;

@Component
public class UniqueHouseValidator implements ConstraintValidator<UniqueHouseConstraint, House>{
	
	@Autowired
	HouseRepository houseRepository;

	UniqueHouseValidator() {
		//System.out.println("public class UniqueApartmentValidator implements ConstraintValidator<UniqueApartmentConstraint, Apartment>");
	}
	
	@Override
	public void initialize(UniqueHouseConstraint constraint) {
			   org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	public boolean isValid(House value, ConstraintValidatorContext context) {
		System.out.println(value);
		String street = value.getStreet();
		String number = value.getNumber();
		boolean answer = true;
		for (House h : houseRepository.findAll()) {
			System.out.println(h);
			if (h.getNumber().equals(number) && h.getStreet().equals(street)) {
				answer = false;
				break;
			}
		}
		return answer;
	}

}
