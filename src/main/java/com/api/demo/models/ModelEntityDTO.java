package com.api.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
public class ModelEntityDTO {

    private String entityName;

    public ModelEntityDTO() {
    }

    public ModelEntityDTO(String entityName) {
        this.entityName = entityName;
    }



}
