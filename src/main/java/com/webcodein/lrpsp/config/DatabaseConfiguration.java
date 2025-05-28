//package com.webcodein.lrpsp.config;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
//
///**
// * Configuration class for setting up reactive MongoDB components.
// * Extends {@link AbstractReactiveMongoConfiguration} to provide MongoDB connection settings.
// */
//@EnableReactiveMongoRepositories  // Enables scanning for reactive MongoDB repositories
//@Configuration                     // Marks this class as a Spring configuration class
//public class DatabaseConfiguration extends AbstractReactiveMongoConfiguration {
//
//    /**
//     * Defines a {@link MongoClient} bean using the default connection string (localhost).
//     *
//     * @return a reactive MongoDB client
//     */
//    @Bean
//    public MongoClient mongoClient() {
//        return MongoClients.create(); // Uses default settings: mongodb://localhost:27017
//    }
//
//    /**
//     * Provides the name of the MongoDB database to connect to.
//     *
//     * @return the name of the database
//     */
//    @Override
//    protected String getDatabaseName() {
//        return "webcodein-learn-reactive-programming-db";
//    }
//
//    /**
//     * Defines the {@link ReactiveMongoTemplate} bean for reactive MongoDB operations.
//     *
//     * @return a configured instance of ReactiveMongoTemplate
//     */
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
//    }
//}
