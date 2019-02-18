package com.api.demo.controllers;

import com.api.demo.models.ModelEntity;
import com.api.demo.services.EntityService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/entities")
public class EntityController {
    private final EntityService service;

    @GetMapping
    public ResponseEntity<List<ModelEntity>> getAllEntities() {
        return ResponseEntity.ok(this.service.findAll());

    }


}
