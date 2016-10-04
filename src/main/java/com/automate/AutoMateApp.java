package com.automate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class}) 
public class AutoMateApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AutoMateApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AutoMateApp.class);
	}
}
