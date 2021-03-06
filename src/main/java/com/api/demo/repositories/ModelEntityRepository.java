package com.api.demo.repositories;


import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelEntityRepository extends JpaRepository<ModelEntity, Integer> {

    List<ModelEntity> findAll();

}
