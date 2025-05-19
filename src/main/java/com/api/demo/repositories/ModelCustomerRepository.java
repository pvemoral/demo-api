package com.api.demo.repositories;

import com.api.demo.models.ModelCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Customer entity
 * Extends JpaRepository to inherit CRUD operations for Customer entity
 * 
 * @author pvemoral
 * @version 1.0
 */
@Repository
public interface ModelCustomerRepository extends JpaRepository<ModelCustomer, Integer> {
    
    /**
     * Retrieve all customers
     * 
     * @return list of customers
     */
    List<ModelCustomer> findAll();
}
