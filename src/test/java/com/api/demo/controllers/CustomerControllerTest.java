package com.api.demo.controllers;

import com.api.demo.models.ModelCustomer;
import com.api.demo.models.ModelCustomerDTO;
import com.api.demo.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CustomerController class.
 * Tests all controller endpoints with mock service interactions.
 * 
 * @author pvemoral
 */
public class CustomerControllerTest {

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController controller;

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
     * Test for getting all customers.
     * Verifies that the controller returns all customers from the service.
     */
    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<ModelCustomer> mockCustomers = Arrays.asList(mockCustomer);
        when(service.findAll()).thenReturn(mockCustomers);

        // Act
        ResponseEntity<List<ModelCustomer>> response = controller.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomers, response.getBody());
        verify(service, times(1)).findAll();
    }

    /**
     * Test for getting a customer by ID when the customer exists.
     * Verifies that the controller returns the customer with OK status.
     */
    @Test
    public void testGetCustomerById_WhenCustomerExists() {
        // Arrange
        when(service.findById(1)).thenReturn(Optional.of(mockCustomer));

        // Act
        ResponseEntity response = controller.getCustomerById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(service, times(1)).findById(1);
    }

    /**
     * Test for getting a customer by ID when the customer doesn't exist.
     * Verifies that the controller returns a NO_CONTENT status.
     */
    @Test
    public void testGetCustomerById_WhenCustomerDoesNotExist() {
        // Arrange
        when(service.findById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity response = controller.getCustomerById(999);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).findById(999);
    }

    /**
     * Test for adding a new customer.
     * Verifies that the controller returns the created customer with CREATED status.
     */
    @Test
    public void testAddCustomer() {
        // Arrange
        when(service.addCustomer(any(ModelCustomerDTO.class))).thenReturn(mockCustomer);

        // Act
        ResponseEntity<ModelCustomer> response = controller.addCustomer(mockCustomerDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(service, times(1)).addCustomer(any(ModelCustomerDTO.class));
    }

    /**
     * Test for updating a customer when the customer exists.
     * Verifies that the controller returns the updated customer with OK status.
     */
    @Test
    public void testUpdateCustomer_WhenCustomerExists() {
        // Arrange
        when(service.findById(1)).thenReturn(Optional.of(mockCustomer));
        when(service.updateCustomer(eq(1), any(ModelCustomerDTO.class))).thenReturn(mockCustomer);

        // Act
        ResponseEntity response = controller.updateCustomer(1, mockCustomerDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(service, times(1)).findById(1);
        verify(service, times(1)).updateCustomer(eq(1), any(ModelCustomerDTO.class));
    }

    /**
     * Test for updating a customer when the customer doesn't exist.
     * Verifies that the controller returns a NO_CONTENT status.
     */
    @Test
    public void testUpdateCustomer_WhenCustomerDoesNotExist() {
        // Arrange
        when(service.findById(999)).thenReturn(Optional.empty());

        // Act
        ResponseEntity response = controller.updateCustomer(999, mockCustomerDTO);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).findById(999);
        verify(service, never()).updateCustomer(anyInt(), any(ModelCustomerDTO.class));
    }

    /**
     * Test for deleting a customer.
     * Verifies that the controller returns OK status and calls the service delete method.
     */
    @Test
    public void testDeleteCustomerById() {
        // Arrange
        doNothing().when(service).deleteCustomer(1);

        // Act
        ResponseEntity response = controller.deleteCustomerById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, times(1)).deleteCustomer(1);
    }
}
