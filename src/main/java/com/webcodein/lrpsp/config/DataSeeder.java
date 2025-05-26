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

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(CustomerRepository customerRepo, OrderRepository orderRepo) {
        return args -> {
            if (customerRepo.count() > 0) {
                System.out.println("❌ Already initialized. Skipping...");
                return;
            }

            List<Customer> customers = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 100_000; i++) {
                Customer customer = new Customer("Customer_" + i, "Job_" + (i % 50));
                customers.add(customer);

                int orderCount = 1 + random.nextInt(3); // 1-3 orders
                for (int j = 0; j < orderCount; j++) {
                    double total = 100 + random.nextDouble() * 900;
                    double discount = total * (random.nextDouble() * 0.2);
                    orders.add(new Order(customer.getId(), total, discount));
                }

                // Save in batches of 1000
                if (customers.size() == 1000) {
                    customerRepo.saveAll(customers);
                    orderRepo.saveAll(orders);
                    System.out.println("Inserted batch up to: " + i);
                    customers.clear();
                    orders.clear();
                }
            }

            // Save the remaining
            if (!customers.isEmpty()) {
                customerRepo.saveAll(customers);
                orderRepo.saveAll(orders);
                System.out.println("Inserted final batch.");
            }

            System.out.println("✅ Finished seeding 100,000 customers and their orders.");
        };
    }
}
