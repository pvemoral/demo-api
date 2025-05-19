package com.api.demo.models;

import lombok.Data;

/**
 * Data Transfer Object for Customer entity
 * 
 * @author pvemoral
 * @version 1.0
 */
@Data
public class ModelCustomerDTO {
    
    /**
     * First name of the customer
     */
    private String firstName;
    
    /**
     * Last name of the customer
     */
    private String lastName;
    
    /**
     * DNI (Documento Nacional de Identidad) - National Identity Document
     */
    private String dni;
    
    /**
     * Default constructor
     */
    public ModelCustomerDTO() {
    }
    
    /**
     * Constructor with all fields
     * 
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param dni the DNI of the customer
     */
    public ModelCustomerDTO(String firstName, String lastName, String dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }
}
