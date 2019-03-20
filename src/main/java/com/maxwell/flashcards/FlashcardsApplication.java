package com.maxwell.flashcards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashcardsApplication {

	
	//CRIAR FRONT END
	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner init() {
		return (args) -> {
		};
	}

}
