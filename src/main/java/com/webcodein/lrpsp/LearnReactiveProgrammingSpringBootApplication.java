package com.webcodein.lrpsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main entry point for the Learn Reactive Programming Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot's auto-configuration and component scanning features.
 */
@SpringBootApplication // Enables auto-configuration, component scanning, and Spring Boot setup
@EnableMongoRepositories(basePackages = "com.webcodein.lrpsp.repository")
// Explicitly enables scanning of MongoDB repositories in the specified package
public class LearnReactiveProgrammingSpringBootApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveProgrammingSpringBootApplication.class, args);
    }

}
