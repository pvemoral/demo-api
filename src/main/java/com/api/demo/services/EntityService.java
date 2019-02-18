package com.api.demo.services;

import com.api.demo.models.ModelEntity;

import java.util.List;
import java.util.Optional;

public interface EntityService {

    List<ModelEntity> findAll();
    Optional<ModelEntity> findByName(String name);
    ModelEntity addEntity(ModelEntity entity);
    ModelEntity updateEntity(ModelEntity entity);
    void deleteEntity(Integer id);

}
