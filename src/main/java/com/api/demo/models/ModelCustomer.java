package com.api.demo.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity representing a Customer in the system.
 * Contains basic customer information including name, last name and DNI.
 * 
 * @author pvemoral
 */
@Data
@Entity
@Table(name = "customer")
public class ModelCustomer {
    
    /**
     * The unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
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
     * This is a unique Spanish identification number.
     */
    @Column(unique = true)
    private String dni;
    
    /**
     * Default constructor.
     */
    public ModelCustomer() {
    }
    
    /**
     * Constructor with all fields except id.
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
     * Full constructor with all fields.
     * 
     * @param id the id of the customer
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
