package com.api.demo.repositories;

import com.api.demo.models.ModelCustomer;
import com.api.demo.models.ModelCustomerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Interface defining the persistence operations for Customer entity
 * 
 * @author pvemoral
 * @version 1.0
 */
public interface CustomerPersistence {
    
    /**
     * Retrieve all customers
     * 
     * @return list of customers
     */
    List<ModelCustomer> findAll();
    
    /**
     * Find a customer by its ID
     * 
     * @param customerId the ID of the customer to find
     * @return an Optional containing the found customer, or empty if not found
     */
    Optional<ModelCustomer> findById(Integer customerId);
    
    /**
     * Add a new customer
     * 
     * @param customer the customer data to add
     * @return the saved customer with its generated ID
     */
    ModelCustomer addCustomer(ModelCustomerDTO customer);
    
    /**
     * Update an existing customer
     * 
     * @param customerId the ID of the customer to update
     * @param customer the new customer data
     * @return the updated customer
     */
    ModelCustomer updateCustomer(Integer customerId, ModelCustomerDTO customer);
    
    /**
     * Delete a customer by its ID
     * 
     * @param customerId the ID of the customer to delete
     */
    void deleteById(Integer customerId);
}
