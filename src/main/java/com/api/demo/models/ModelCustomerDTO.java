package com.api.demo.models;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for Customer information.
 * Used for transferring customer data between processes without exposing 
 * the internal entity representation.
 * 
 * @author pvemoral
 */
@Data
public class ModelCustomerDTO {
    
    /**
     * The first name of the customer.
     */
    private String firstName;
    
    /**
     * The last name of the customer.
     */
    private String lastName;
    
    /**
     * The DNI (Documento Nacional de Identidad) of the customer.
     */
    private String dni;
}
