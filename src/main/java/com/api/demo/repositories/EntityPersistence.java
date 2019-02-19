package com.api.demo.repositories;

import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;

import java.util.List;
import java.util.Optional;

public interface EntityPersistence {

    List<ModelEntity> findAll();
    Optional<ModelEntity> findByName(String name);
    Optional<ModelEntity> findById(Integer entityId);
    ModelEntity addEntity(ModelEntityDTO entity);
    ModelEntity updateEntity(ModelEntity entity);
    void deleteEntity(Integer id);
}
