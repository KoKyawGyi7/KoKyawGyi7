package com.cyberz.ar7demon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cyberz.ar7demon")
public class Ar7demonApplication {
	public static void main(String[] args) {
		SpringApplication.run(Ar7demonApplication.class, args);
	}
}

