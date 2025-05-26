package com.webcodein.lrpsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.webcodein.lrpsp.repository")
public class LearnReactiveProgrammingSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnReactiveProgrammingSpringBootApplication.class, args);
    }

}
