package com.api.demo.controllers;

import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import com.api.demo.services.EntityService;
import jdk.nashorn.internal.ir.Optimistic;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/entities")
public class EntityController {
    private final EntityService service;

    @GetMapping
    public ResponseEntity<List<ModelEntity>> getAllEntities() {
        return ResponseEntity.ok(this.service.findAll());

    }

    @GetMapping("/{entityId}")
    public ResponseEntity<ModelEntity> getEntityById(
            @PathVariable Integer entityId) {
        Optional<ModelEntity> entity = this.service.findById(entityId);
        return  ResponseEntity.of(entity);


    }

    @PostMapping
    public ResponseEntity<ModelEntity> addEntity(
            @RequestBody ModelEntityDTO entity ) {

      //  String name = entity.getEntityName();

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(this.service.addEntity(entity));


    }


}
