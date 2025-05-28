package com.webcodein.lrpsp.config;

import com.webcodein.lrpsp.model.Customer;
import com.webcodein.lrpsp.model.Order;
import com.webcodein.lrpsp.repository.CustomerRepository;
import com.webcodein.lrpsp.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Configuration class for seeding initial data into the MongoDB database.
 * This class runs once on application startup and populates 100,000 customers
 * along with 1–3 orders per customer (randomized), only if the database is empty.
 */
@Configuration
public class DataSeeder {

    /**
     * Defines a CommandLineRunner bean that seeds customer and order data on application startup.
     *
     * @param customerRepo the repository for customer entities
     * @param orderRepo    the repository for order entities
     * @return a runner that performs the data seeding
     */
    @Bean
    public CommandLineRunner seedData(CustomerRepository customerRepo, OrderRepository orderRepo) {
        return args -> {
            // Check if database is already initialized
            if (customerRepo.count() > 0) {
                System.out.println("❌ Already initialized. Skipping...");
                return;
            }

            List<Customer> customers = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 100_000; i++) {
                // Create a new customer with a unique name and a cyclic job title
                Customer customer = new Customer("Customer_" + i, "Job_" + (i % 50));
                customers.add(customer);

                // Create 1–3 orders per customer with random total and discount
                int orderCount = 1 + random.nextInt(3);
                for (int j = 0; j < orderCount; j++) {
                    double total = 100 + random.nextDouble() * 900; // total between 100 and 1000
                    double discount = total * (random.nextDouble() * 0.2); // up to 20% discount
                    orders.add(new Order(customer.getId(), total, discount));
                }

                // Batch insert every 1000 customers and associated orders
                if (customers.size() == 1000) {
                    customerRepo.saveAll(customers); // Save batch of customers
                    orderRepo.saveAll(orders);       // Save batch of orders
                    System.out.println("Inserted batch up to: " + i);
                    customers.clear();
                    orders.clear();
                }
            }

            // Save any remaining records that didn't make up a full batch
            if (!customers.isEmpty()) {
                customerRepo.saveAll(customers);
                orderRepo.saveAll(orders);
                System.out.println("Inserted final batch.");
            }

            System.out.println("✅ Finished seeding 100,000 customers and their orders.");
        };
    }
}