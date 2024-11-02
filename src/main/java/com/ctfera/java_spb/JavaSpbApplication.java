package com.ctfera.java_spb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //Ativando Schedule
public class JavaSpbApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpbApplication.class, args);
	}

}
