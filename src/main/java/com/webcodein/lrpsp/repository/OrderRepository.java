package com.webcodein.lrpsp.repository;

import com.webcodein.lrpsp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
