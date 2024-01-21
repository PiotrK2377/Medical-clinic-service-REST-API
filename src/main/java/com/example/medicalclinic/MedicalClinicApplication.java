package com.example.medicalclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicalClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalClinicApplication.class, args);
	}

}
