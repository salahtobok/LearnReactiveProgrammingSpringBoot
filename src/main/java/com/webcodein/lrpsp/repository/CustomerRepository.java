package com.webcodein.lrpsp.repository;

import com.webcodein.lrpsp.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
