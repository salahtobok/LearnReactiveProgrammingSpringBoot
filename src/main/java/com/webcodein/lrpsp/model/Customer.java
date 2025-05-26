package com.webcodein.lrpsp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Represents a Customer entity stored in the MongoDB database.
 */
@Document // Marks this class as a MongoDB document
public class Customer {

    @Id
    private String id;     // Unique identifier for the customer

    private String name;   // Customer's name
    private String job;    // Customer's job title

    /**
     * Constructs a new Customer with the given name and job.
     * A unique UUID is automatically generated as the ID.
     *
     * @param name the customer's name
     * @param job  the customer's job title
     */
    public Customer(String name, String job) {
        this.id = UUID.randomUUID().toString(); // Automatically generate a unique ID
        this.name = name;
        this.job = job;
    }

    // --- Getters and Setters ---

    /**
     * Gets the customer ID.
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the customer ID.
     * @param id the new ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the customer's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer's job.
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets the customer's job.
     * @param job the new job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Returns a string representation of the Customer object.
     * @return a string containing id, name, and job
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
