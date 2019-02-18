package com.api.demo.services;

import com.api.demo.models.ModelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.demo.repositories.ModelEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityServiceImpl implements EntityService {

    private final ModelEntityRepository repository;

    @Override
    public List<ModelEntity> findAll() {
        return(repository.findAll());
    }

    @Override
    public Optional<ModelEntity> findByName(String name) {
        return repository.findByEntityName(name);

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
