package com.ayush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAsync
@SpringBootApplication
public class HospitalManagementProjectApplication {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		SpringApplication.run(HospitalManagementProjectApplication.class, args);
	}

}
