package ru.vallball.jkh01.jail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.vallball.jkh01.model.Apartment;
import ru.vallball.jkh01.repository.HouseRepository;
import ru.vallball.jkh01.service.HouseService;

@Component
public class ApartmentValidator implements ConstraintValidator<ApartmentConstraint, Apartment> {

	public ApartmentValidator() {
		//System.out.println("public class ApartmentValidator implements ConstraintValidator<ApartmentConstraint, Apartment>");
	}

	@Override
	public void initialize(ApartmentConstraint constraint) {
		//org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public boolean isValid(Apartment value, ConstraintValidatorContext context) {
		if (value.getEntrance() > value.getHouse().getEntrances() || value.getLevel() > value.getHouse().getLevels()) {
			return false;
		} else {
			return true;
		}
	}

}
