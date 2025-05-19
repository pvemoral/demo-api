package com.api.demo.services;

import com.api.demo.models.ModelCustomer;
import com.api.demo.models.ModelCustomerDTO;
import com.api.demo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that handles the business logic for Customer entities.
 * This class acts as an intermediary between the controller and repository layers.
 * 
 * @author pvemoral
 */
@Service
public class CustomerService {
    
    private final CustomerRepository repository;
    
    /**
     * Constructor with repository dependency injection.
     * 
     * @param repository the repository to use for data access
     */
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Retrieves all customers from the database.
     * 
     * @return a list of all customers
     */
    public List<ModelCustomer> findAll() {
        return repository.findAll();
    }
    
    /**
     * Finds a customer by their ID.
     * 
     * @param id the ID of the customer to find
     * @return an Optional containing the customer if found, or an empty Optional if not found
     */
    public Optional<ModelCustomer> findById(Integer id) {
        return repository.findById(id);
    }
    
    /**
     * Finds a customer by their DNI.
     * 
     * @param dni the DNI of the customer to find
     * @return an Optional containing the customer if found, or an empty Optional if not found
     */
    public Optional<ModelCustomer> findByDni(String dni) {
        return repository.findByDni(dni);
    }
    
    /**
     * Adds a new customer to the database.
     * 
     * @param customerDTO the DTO containing the customer data
     * @return the saved customer entity with its generated ID
     */
    public ModelCustomer addCustomer(ModelCustomerDTO customerDTO) {
        ModelCustomer customer = new ModelCustomer(
            customerDTO.getFirstName(),
            customerDTO.getLastName(),
            customerDTO.getDni()
        );
        return repository.save(customer);
    }
    
    /**
     * Updates an existing customer.
     * 
     * @param id the ID of the customer to update
     * @param customerDTO the DTO containing the updated customer data
     * @return the updated customer entity
     */
    public ModelCustomer updateCustomer(Integer id, ModelCustomerDTO customerDTO) {
        ModelCustomer customer = new ModelCustomer(
            id,
            customerDTO.getFirstName(),
            customerDTO.getLastName(),
            customerDTO.getDni()
        );
        return repository.save(customer);
    }
    
    /**
     * Deletes a customer by their ID.
     * 
     * @param id the ID of the customer to delete
     */
    public void deleteCustomer(Integer id) {
        repository.deleteById(id);
    }
}
