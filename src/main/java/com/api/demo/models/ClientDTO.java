package com.api.demo.models;

import lombok.Data;

@Data
public class ClientDTO {

    private String name;
    private String address;
    private String phone;
    private String dni;

    public ClientDTO() {
    }

    public ClientDTO(String name, String address, String phone, String dni) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dni = dni;
    }
}
