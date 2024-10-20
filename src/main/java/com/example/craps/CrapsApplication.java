package com.example.craps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableJpaAuditing(modifyOnCreate = false)
public class CrapsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrapsApplication.class, args);
	}

}
