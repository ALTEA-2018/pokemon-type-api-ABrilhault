package com.miage.altea.tp.pokemon_type_api;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String... args){
		SpringApplication.run(Application.class, args);
	}

}
