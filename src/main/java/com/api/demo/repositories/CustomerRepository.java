package com.api.demo.repositories;

import com.api.demo.models.ModelCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Customer entities, provides data access methods.
 * Extends JpaRepository to inherit common CRUD operations.
 * 
 * @author pvemoral
 */
@Repository
public interface CustomerRepository extends JpaRepository<ModelCustomer, Integer> {
    
    /**
     * Find a customer by their DNI.
     * 
     * @param dni the DNI to search for
     * @return an Optional containing the customer if found, or an empty Optional if not found
     */
    Optional<ModelCustomer> findByDni(String dni);
}
