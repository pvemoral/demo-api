package com.api.demo.repositories;

import com.api.demo.models.ModelEntity;

import java.util.List;
import java.util.Optional;

public interface EntityPersistence {

    List<ModelEntity> findAll();
    Optional<ModelEntity> findByName(String name);
    ModelEntity addEntity(ModelEntity entity);
    ModelEntity updateEntity(ModelEntity entity);
    void deleteEntity(Integer id);
}
