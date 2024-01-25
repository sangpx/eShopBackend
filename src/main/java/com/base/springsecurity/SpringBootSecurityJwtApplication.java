package com.base.springsecurity;

import com.base.springsecurity.services.FilesStorageService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

	@Resource
	private FilesStorageService storageService;

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

	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		storageService.init();
	}

}
