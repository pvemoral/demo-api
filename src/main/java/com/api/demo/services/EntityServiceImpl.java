package com.api.demo.services;

import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import com.api.demo.repositories.EntityPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.demo.repositories.ModelEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityServiceImpl implements EntityService {

    private final EntityPersistence repository;

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
    public ModelEntity updateEntity(Integer entityId, ModelEntityDTO entity) {
        return repository.updateEntity(entityId, entity);

    }

    @Override
    public void deleteEntity(Integer entityId) {
        repository.deleteById(entityId);

    }
}
