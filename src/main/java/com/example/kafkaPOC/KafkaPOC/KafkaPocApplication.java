package com.example.kafkaPOC.KafkaPOC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KafkaPocApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaPocApplication.class, args);
		System.out.println("Starting Kafka Application");
	}

}
