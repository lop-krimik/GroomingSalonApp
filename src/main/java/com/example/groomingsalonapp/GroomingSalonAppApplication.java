package com.example.groomingsalonapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GroomingSalonAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroomingSalonAppApplication.class, args);
    }

}
