package com.api.demo.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String phone;
    private String dni;

    public Client() {
    }

    public Client(String name, String address, String phone, String dni) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dni = dni;
    }

    public Client(Integer id, String name, String address, String phone, String dni) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dni = dni;
    }
}
