package ru.vallball.jkh01.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueApartmentValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueApartmentConstraint {

	String message() default "The number of the apartment must be unique in the same house";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
