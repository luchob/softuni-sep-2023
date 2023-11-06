package org.softuni.mobilele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MobileleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileleApplication.class, args);
	}

}
