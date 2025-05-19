package com.api.demo.services;

import com.api.demo.models.ModelCustomer;
import com.api.demo.models.ModelCustomerDTO;
import com.api.demo.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CustomerService class.
 * Tests all service methods with mock repository interactions.
 * 
 * @author pvemoral
 */
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    private ModelCustomer mockCustomer;
    private ModelCustomerDTO mockCustomerDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        // Initialize test data
        mockCustomer = new ModelCustomer(1, "John", "Doe", "12345678X");
        mockCustomerDTO = new ModelCustomerDTO();
        mockCustomerDTO.setFirstName("John");
        mockCustomerDTO.setLastName("Doe");
        mockCustomerDTO.setDni("12345678X");
    }

    /**
     * Test for finding all customers.
     * Verifies that the service returns all customers from the repository.
     */
    @Test
    public void testFindAll() {
        // Arrange
        List<ModelCustomer> mockCustomers = Arrays.asList(mockCustomer);
        when(repository.findAll()).thenReturn(mockCustomers);

        // Act
        List<ModelCustomer> result = service.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(mockCustomer, result.get(0));
        verify(repository, times(1)).findAll();
    }

    /**
     * Test for finding a customer by ID.
     * Verifies that the service returns the correct customer when it exists.
     */
    @Test
    public void testFindById_WhenCustomerExists() {
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.of(mockCustomer));

        // Act
        Optional<ModelCustomer> result = service.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockCustomer, result.get());
        verify(repository, times(1)).findById(1);
    }

    /**
     * Test for finding a customer by ID when the customer doesn't exist.
     * Verifies that the service returns an empty Optional.
     */
    @Test
    public void testFindById_WhenCustomerDoesNotExist() {
        // Arrange
        when(repository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<ModelCustomer> result = service.findById(999);

        // Assert
        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(999);
    }

    /**
     * Test for finding a customer by DNI.
     * Verifies that the service returns the correct customer when it exists.
     */
    @Test
    public void testFindByDni_WhenCustomerExists() {
        // Arrange
        when(repository.findByDni("12345678X")).thenReturn(Optional.of(mockCustomer));

        // Act
        Optional<ModelCustomer> result = service.findByDni("12345678X");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockCustomer, result.get());
        verify(repository, times(1)).findByDni("12345678X");
    }

    /**
     * Test for adding a new customer.
     * Verifies that the service correctly creates and saves a new customer.
     */
    @Test
    public void testAddCustomer() {
        // Arrange
        when(repository.save(any(ModelCustomer.class))).thenReturn(mockCustomer);

        // Act
        ModelCustomer result = service.addCustomer(mockCustomerDTO);

        // Assert
        assertEquals(mockCustomer, result);
        verify(repository, times(1)).save(any(ModelCustomer.class));
    }

    /**
     * Test for updating an existing customer.
     * Verifies that the service correctly updates and saves an existing customer.
     */
    @Test
    public void testUpdateCustomer() {
        // Arrange
        ModelCustomer expectedUpdatedCustomer = new ModelCustomer(1, "John", "Doe", "12345678X");
        when(repository.save(any(ModelCustomer.class))).thenReturn(expectedUpdatedCustomer);

        // Act
        ModelCustomer result = service.updateCustomer(1, mockCustomerDTO);

        // Assert
        assertEquals(expectedUpdatedCustomer, result);
        verify(repository, times(1)).save(any(ModelCustomer.class));
    }

    /**
     * Test for deleting a customer.
     * Verifies that the service correctly calls the delete method on the repository.
     */
    @Test
    public void testDeleteCustomer() {
        // Arrange
        doNothing().when(repository).deleteById(1);

        // Act
        service.deleteCustomer(1);

        // Assert
        verify(repository, times(1)).deleteById(1);
    }
}
