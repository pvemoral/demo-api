package com.api.demo.controllers;

import com.api.demo.models.ModelCustomer;
import com.api.demo.models.ModelCustomerDTO;
import com.api.demo.services.CustomerService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Customer entities.
 * Provides endpoints for CRUD operations on customers.
 * 
 * @author pvemoral
 */
@Data
@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private final CustomerService service;
    
    /**
     * Retrieves all customers.
     * 
     * @return a response entity containing a list of all customers
     */
    @GetMapping
    public ResponseEntity<List<ModelCustomer>> getAllCustomers() {
        return ResponseEntity.ok(this.service.findAll());
    }
    
    /**
     * Retrieves a customer by their ID.
     * 
     * @param customerId the ID of the customer to retrieve
     * @return a response entity containing the customer if found, or a no-content response if not found
     */
    @GetMapping("/{customerId}")
    public ResponseEntity getCustomerById(@PathVariable Integer customerId) {
        Optional<ModelCustomer> customer = this.service.findById(customerId);
        if(customer.isPresent())
            return ResponseEntity.of(customer);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON_UTF8).body("");
    }
    
    /**
     * Creates a new customer.
     * 
     * @param customer the DTO containing the customer data
     * @return a response entity containing the created customer
     */
    @PostMapping
    public ResponseEntity<ModelCustomer> addCustomer(@RequestBody ModelCustomerDTO customer) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(this.service.addCustomer(customer));
    }
    
    /**
     * Updates an existing customer.
     * 
     * @param customerId the ID of the customer to update
     * @param customer the DTO containing the updated customer data
     * @return a response entity containing the updated customer if found, or a no-content response if not found
     */
    @PutMapping("/{customerId}")
    public ResponseEntity updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody ModelCustomerDTO customer) {
        
        Optional<ModelCustomer> foundCustomer = this.service.findById(customerId);
        
        if(!foundCustomer.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        
        ModelCustomer customerUpdated = this.service.updateCustomer(customerId, customer);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(customerUpdated);
    }
    
    /**
     * Deletes a customer by their ID.
     * 
     * @param customerId the ID of the customer to delete
     * @return a response entity with OK status
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable Integer customerId) {
        this.service.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body("");
    }
}
