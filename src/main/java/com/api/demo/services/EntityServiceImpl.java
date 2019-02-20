package com.api.demo.services;

import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import com.api.demo.repositories.EntityPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService {

    private final EntityPersistence repository;

    public EntityServiceImpl(EntityPersistence repository) {
        this.repository = repository;
    }

    @Override
    public List<ModelEntity> findAll() {
        return(repository.findAll());
    }

    @Override
    public Optional<ModelEntity> findById(Integer entityId) {
        return this.repository.findById(entityId);
    }

    @Override
    public ModelEntity addEntity(ModelEntityDTO entity) {

        return repository.addEntity(entity);

    }

    @Override
    public ModelEntity updateEntity(ModelEntityDTO entity) {
        return null;
    }

    @Override
    public void deleteEntity(Integer entityId) {
        repository.deleteById(entityId);

    }
}
