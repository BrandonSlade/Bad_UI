package com.revature.Bad_UI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude =  HibernateJpaAutoConfiguration.class)
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}


}
