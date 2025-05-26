package com.webcodein.lrpsp.controller;

import com.webcodein.lrpsp.model.Customer;
import com.webcodein.lrpsp.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Controller for handling reactive endpoints related to Customer and Order operations.
 * Uses ReactiveMongoTemplate for non-blocking MongoDB interactions.
 */
@RestController
public class DataController {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    /**
     * Constructor-based injection of ReactiveMongoTemplate.
     *
     * @param reactiveMongoTemplate the reactive MongoDB template
     */
    public DataController(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    /**
     * Creates a new customer and saves it to the database.
     *
     * @param customer the customer to be created
     * @return Mono emitting the saved Customer object
     */
    @PostMapping("/customer/create")
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        // Save the customer to MongoDB reactively
        return reactiveMongoTemplate.save(customer);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId the ID of the customer to find
     * @return Mono emitting the found Customer object (or empty if not found)
     */
    @GetMapping("/customer/find-by-id")
    public Mono<Customer> findCustomerById(@RequestParam("customerId") String customerId) {
        return getCustomerById(customerId)
                .log(); // Logs signals for debugging and traceability
    }

    /**
     * Creates a new order and saves it to the database.
     *
     * @param order the order to be created
     * @return Mono emitting the saved Order object
     */
    @PostMapping("/order/create")
    public Mono<Order> createOrder(@RequestBody Order order) {
        return reactiveMongoTemplate.save(order);
    }

    /**
     * Calculates the sales summary per customer.
     *
     * @return Mono emitting a map with customer names as keys and their total sales as values
     */
    @GetMapping("/sales/summary")
    public Mono<Map<String, Double>> calculateSalesSummary() {
        return reactiveMongoTemplate.findAll(Customer.class)
                // For each customer, calculate their total sales and combine the result into a tuple
                .flatMap(customer -> Mono.zip(
                        Mono.just(customer),
                        calculateSalesSummary(customer.getId()))
                )
                // Convert the list of tuples to a Map of customer name to total sales
                .collectMap(
                        tuple2 -> tuple2.getT1().getName(), // Key: customer name
                        tuple2 -> tuple2.getT2()            // Value: total sales
                );
    }

    /**
     * Calculates total sales for a specific customer by summing up their order totals.
     *
     * @param customerId the ID of the customer
     * @return Mono emitting the total sales amount for the customer
     */
    private Mono<Double> calculateSalesSummary(String customerId) {
        // Filter orders by customerId
        Criteria criteria = Criteria.where("customerId").is(customerId);
        Query query = Query.query(criteria);

        // Find matching orders, extract totals, and reduce to a total sum
        return reactiveMongoTemplate.find(query, Order.class)
                .map(Order::getTotal) // Extract total from each order
                .reduce(0d, Double::sum); // Sum up all totals
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId the ID of the customer
     * @return Mono emitting the Customer object if found, otherwise empty
     */
    private Mono<Customer> getCustomerById(String customerId) {
        Criteria criteria = Criteria.where("id").is(customerId);
        Query query = new Query(criteria);
        return reactiveMongoTemplate.findOne(query, Customer.class);
    }
}
