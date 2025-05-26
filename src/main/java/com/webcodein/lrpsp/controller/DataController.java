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

@RestController
public class DataController {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DataController(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


    @PostMapping("/customer/create")
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        //Save it to database
        Mono<Customer> savedCustomer = reactiveMongoTemplate.save(customer);
        return savedCustomer;
    }


    @GetMapping("/customer/find-by-id")
    public Mono<Customer> findCustomerById(@RequestParam("customerId") String customerId){
        return getCustomerById(customerId)
                //.log();
                .log();
    }

    @PostMapping("/order/create")
    public Mono<Order> createOrder(@RequestBody Order order){
        return reactiveMongoTemplate.save(order);
    }

    @GetMapping("/sales/summary")
    public Mono<Map<String,Double>> calculateSalesSummary(){
        return reactiveMongoTemplate.findAll(Customer.class)
                .flatMap(customer -> Mono.zip(Mono.just(customer),calculateSalesSummary(customer.getId())))
                .collectMap(tuple2 -> tuple2.getT1().getName(), tuple2 -> tuple2.getT2());
    }

    private Mono<Double> calculateSalesSummary(String customerId){
        Criteria criteria = Criteria.where("customerId").is(customerId);

        return reactiveMongoTemplate.find(Query.query(criteria),Order.class)
                .map(Order::getTotal)
                .reduce(0d,Double::sum);
    }
    private Mono<Customer> getCustomerById(String customerId){
        Criteria criteria = Criteria.where("id").is(customerId);
        Query query = new Query(criteria);
        return reactiveMongoTemplate.findOne(query,Customer.class);
    }
}
