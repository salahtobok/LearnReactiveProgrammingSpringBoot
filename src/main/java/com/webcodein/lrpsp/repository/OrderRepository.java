package com.webcodein.lrpsp.repository;

import com.webcodein.lrpsp.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link Order} entities.
 * <p>
 * Extends {@link MongoRepository} to provide basic CRUD operations and query method
 * support for the Order collection in MongoDB.
 */
public interface OrderRepository extends MongoRepository<Order, String> {
    // Inherits standard CRUD operations: save, findById, findAll, deleteById, etc.
}
