package com.example.ProyectoIntegradorBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProyectoIntegradorBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorBackApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello, world!";
	}

}

