package com.library_a3.library_a3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibraryA3Application {

	public static void main(String[] args) {
		SpringApplication.run(LibraryA3Application.class, args);
	}

}