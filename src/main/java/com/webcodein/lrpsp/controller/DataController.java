package com.webcodein.lrpsp.controller;


import com.webcodein.lrpsp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DataController {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public DataController(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


    @PostMapping("/customer/create")
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        //Save it to database
        Mono<Customer> customerMono = reactiveMongoTemplate.save(customer);

        return customerMono;
    }
}
