package com.api.demo.repositories;

import com.api.demo.models.ModelEntity;

import java.util.List;
import java.util.Optional;

public class EntityPersistenceImpl implements EntityPersistence {
    private ModelEntityRepository repository;

    public EntityPersistenceImpl(ModelEntityRepository modelEntityRepository) {
        this.repository = modelEntityRepository;
    }

    @Override
    public List<ModelEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<ModelEntity> findByName(String name) {

        return this.repository.findByEntityName(name);

    }

    @Override
    public ModelEntity addEntity(ModelEntity entity) {
        return null;
    }

    @Override
    public ModelEntity updateEntity(ModelEntity entity) {
        return null;
    }

    @Override
    public void deleteEntity(Integer id) {

    }
}
