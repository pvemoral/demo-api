package com.api.demo.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity representing a Customer in the system
 * 
 * @author pvemoral
 * @version 1.0
 */
@Data
@Entity
@Table(name = "customer")
public class ModelCustomer {
    
    /**
     * Unique identifier for the customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
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
    public ModelCustomer() {
    }
    
    /**
     * Constructor with all fields except ID
     * 
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param dni the DNI of the customer
     */
    public ModelCustomer(String firstName, String lastName, String dni) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }
    
    /**
     * Constructor with all fields including ID
     * 
     * @param id the identifier of the customer
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param dni the DNI of the customer
     */
    public ModelCustomer(Integer id, String firstName, String lastName, String dni) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }
}
