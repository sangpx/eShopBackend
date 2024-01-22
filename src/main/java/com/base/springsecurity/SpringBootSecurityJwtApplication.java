package com.base.springsecurity;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {
	//Cau hinh modelMapper trong project
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println("                                  -=-=-=-=-=-=-=-=-=-=-=-=-=- Application is Starting -=-=-=-=-=-=-=-=-=-=-=-=-=-");
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
		System.out.println();
		System.out.println("                                  -=-=-=-=-=-=-=-=-=-=-=-=-=- Application is Working Fine -=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}

}
