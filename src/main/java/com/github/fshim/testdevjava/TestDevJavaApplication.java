package com.github.fshim.testdevjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class TestDevJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDevJavaApplication.class, args);
	}

}
