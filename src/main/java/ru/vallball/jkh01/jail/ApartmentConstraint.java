package ru.vallball.jkh01.jail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ApartmentValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ApartmentConstraint {
	String message() default "Number of the entrance or the level must be not more than maximum entrance or level of the house";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
