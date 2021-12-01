package ru.vallball.jkh01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Jkh01Application {

	static final Logger logger = LoggerFactory.getLogger(Jkh01Application.class);
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(Jkh01Application.class, args);
	}

	public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : allBeanNames) {
			System.out.println(beanName);
		}
	}

}
