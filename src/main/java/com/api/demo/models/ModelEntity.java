package com.api.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "entity")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String entityName;

    public ModelEntity() {
    }

    public ModelEntity(String entityName) {
        this.entityName = entityName;
    }

    public ModelEntity(Integer id, String entityName) {
        this.id = id;
        this.entityName = entityName;
    }
}
