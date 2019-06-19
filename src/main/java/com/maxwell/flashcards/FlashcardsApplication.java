package com.maxwell.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlashcardsApplication {

	@Autowired

	public static void main(String[] args) {
		 SpringApplication.run(FlashcardsApplication.class, args);

		/*
		 * String myPassword = "M4xwell";
		 * 
		 * // Generate Salt. The generated value can be stored in DB. String salt =
		 * PasswordUtils.getSalt(30);
		 * 
		 * // Protect user's password. The generated value can be stored in DB. String
		 * mySecurePassword = PasswordUtils.generateSecurePassword(myPassword, salt);
		 * 
		 * // Print out protected password System.out.println("My secure password = " +
		 * mySecurePassword); System.out.println("Salt value = " + salt);
		 */
	}

	@Bean
	CommandLineRunner init() {
		return (args) -> {

		};
	}

}
