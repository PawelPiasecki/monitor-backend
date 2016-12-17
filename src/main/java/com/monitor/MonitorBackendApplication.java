package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MonitorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorBackendApplication.class, args);
	}

}
