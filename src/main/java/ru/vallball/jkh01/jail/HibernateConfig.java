package ru.vallball.jkh01.jail;

import javax.validation.ValidatorFactory;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HibernateConfig {

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(ValidatorFactory validatorFactory) {
    	//System.out.println("_________________--validatorfactory_____= " + validatorFactory);
        return (properties) -> {
            properties.put(org.hibernate.cfg.AvailableSettings.JPA_VALIDATION_FACTORY, validatorFactory);
        };
    }
}
