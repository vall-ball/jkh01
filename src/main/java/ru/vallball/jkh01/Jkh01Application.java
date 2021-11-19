package ru.vallball.jkh01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Jkh01Application {

	//static final Logger logger = LoggerFactory.getLogger(Jkh01Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Jkh01Application.class, args);
	}

}
