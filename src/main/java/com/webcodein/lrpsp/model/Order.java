package com.webcodein.lrpsp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Represents an Order placed by a Customer, stored as a document in MongoDB.
 */
@Document // Indicates that this class is a MongoDB document
public class Order {

    private String id;           // Unique identifier for the order
    private String customerId;   // The ID of the customer who placed the order
    private Double total;        // Total amount for the order
    private Double discount;     // Discount applied to the order

    /**
     * Default constructor needed for deserialization and frameworks.
     */
    public Order() {
    }

    /**
     * Constructs a new Order with a generated ID, customer ID, total amount, and discount.
     *
     * @param customerId the ID of the customer placing the order
     * @param total      the total amount of the order
     * @param discount   the discount applied to the order
     */
    public Order(String customerId, Double total, Double discount) {
        this.id = UUID.randomUUID().toString(); // Auto-generate a unique order ID
        this.customerId = customerId;
        this.total = total;
        this.discount = discount;
    }

    // --- Getters and Setters ---

    /**
     * Gets the order ID.
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the order ID.
     * @param id the new ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the customer ID associated with this order.
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID for this order.
     * @param customerId the new customer ID
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the total amount of the order.
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Sets the total amount for the order.
     * @param total the total value
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * Gets the discount applied to the order.
     * @return the discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * Sets the discount for the order.
     * @param discount the new discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Returns a string representation of the Order.
     * @return a formatted string with order details
     */
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
