package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.monitor")
public class MonitorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorBackendApplication.class, args);
	}

}
