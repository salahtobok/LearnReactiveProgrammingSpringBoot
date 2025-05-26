package com.webcodein.lrpsp.repository;

import com.webcodein.lrpsp.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing {@link Customer} entities.
 * <p>
 * Extends {@link MongoRepository} to provide CRUD operations and query method support
 * for the Customer collection in MongoDB.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // No additional methods required; inherits standard CRUD functionality.
}
