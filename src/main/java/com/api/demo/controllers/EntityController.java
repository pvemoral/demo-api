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

import javax.annotation.Nullable;
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
    public ResponseEntity getEntityById(
            @PathVariable Integer entityId) {
        Optional<ModelEntity> entity = this.service.findById(entityId);
        if(entity.isPresent())
            return ResponseEntity.of(entity);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON_UTF8).body("");
    }

    @PostMapping
    public ResponseEntity<ModelEntity> addEntity(
            @RequestBody ModelEntityDTO entity ) {

        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(this.service.addEntity(entity));
    }

    @DeleteMapping("/{entityId}")
    public ResponseEntity deleteEntityById(
            @PathVariable Integer entityId) {
        this.service.deleteEntity(entityId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body("");
    }

    @PutMapping("/{entityId}")
    public ResponseEntity updateEntity(
            @PathVariable Integer entityId,
            @RequestBody ModelEntityDTO entity ) {

        Optional<ModelEntity> foundEntity = this.service.findById(entityId);

        if(!foundEntity.isPresent()) {
           return ResponseEntity.noContent().build();
        }

        ModelEntity entityUpdated = this.service.updateEntity(entityId, entity);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(entityUpdated);

    }

}
