package com.api.demo.services;

import com.api.demo.models.ModelEntityDTO;
import com.api.demo.models.ModelEntity;
import java.util.List;
import java.util.Optional;

public interface EntityService {

    List<ModelEntity> findAll();
    Optional<ModelEntity> findByName(String name);
    Optional<ModelEntity> findById(Integer entityId);
    ModelEntity addEntity(ModelEntityDTO entity);
    ModelEntity updateEntity(ModelEntityDTO entity);
    void deleteEntity(Integer id);


}
