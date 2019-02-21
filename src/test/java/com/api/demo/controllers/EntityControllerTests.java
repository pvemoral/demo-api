package com.api.demo.controllers;

import com.api.demo.controllers.EntityController;
import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import com.api.demo.repositories.ModelEntityRepository;
import com.api.demo.services.EntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.text.html.Option;
import java.util.*;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

import java.util.List;



import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@WebMvcTest(EntityController.class)
public class EntityControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private EntityService service;

    private List<ModelEntity> entities;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        this.entities = Collections.singletonList(new ModelEntity(1,"Ester"));

    }

    @Test
    public void whenGetFindAll_thenReturnList()
            throws Exception {


        given(service.findAll()).willReturn(this.entities);

        mvc.perform(get("/entities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].entityName", is(this.entities.get(0).getEntityName())));
    }

    @Test
    public void whenGetFindAll_thenReturnEmptyList()
            throws Exception {

        this.entities = Collections.emptyList();

        given(service.findAll()).willReturn(this.entities);

        mvc.perform(get("/entities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenAddNewEntity_thenReturnAddedEntity()
            throws Exception {

        this.entities = Collections.emptyList();

        ModelEntityDTO andrea = new ModelEntityDTO("Andrea");
        ModelEntity andreaAdded = new ModelEntity(1,"Andrea");

        given(service.addEntity(andrea)).willReturn(andreaAdded);
        String json = mapper.writeValueAsString(andrea);

        mvc.perform(post("/entities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(andreaAdded.getId())))
                .andExpect(jsonPath("$.entityName",is(andreaAdded.getEntityName())));

    }

    @Test
    public void whenFindByIdinEmptyDB_thenReturnEmptyResult()
            throws Exception {

        this.entities = Collections.emptyList();

        given(service.findById(1)).willReturn(Optional.empty());

        mvc.perform(get("/entities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void whenFindById_andIdNonExistOnDB_thenReturnEmptyResult()
            throws Exception {

        given(service.findById(1)).willReturn(Optional.empty());

        mvc.perform(get("/entities/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void whenFindById_andIdExistOnDB_thenReturnEntityResult()
            throws Exception {

        ModelEntity ester  = new ModelEntity(1,"Ester");
        given(service.findById(1)).willReturn(Optional.of(ester));


        mvc.perform(get("/entities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(ester.getId())))
                .andExpect(jsonPath("$.entityName",is(ester.getEntityName())));
    }


    @Test
    public void whenDeleteByIdNonExistId_thenReturnEmptyResultAndOK()
            throws Exception {

        this.entities = Collections.emptyList();

        mvc.perform(delete("/entities/17")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void whenDeleteByIdExistId_thenReturnEmptyResultAndOK()
            throws Exception {

        this.entities = Collections.emptyList();

        mvc.perform(delete("/entities/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void whenUpdateById_andIdNonExistOnDB_thenReturnEmptyResult()
            throws Exception {
        ModelEntityDTO andrea = new ModelEntityDTO("Andrea");

        given(service.updateEntity(10,andrea)).willReturn(null);

        String json = mapper.writeValueAsString(andrea);

        mvc.perform(put("/entities/10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void whenUpdateById_andIdExistOnDB_thenReturnUpdatedEntityResult()
            throws Exception {

        ModelEntityDTO andrea  = new ModelEntityDTO("Andrea");
        ModelEntity adreaUpdated = new ModelEntity(1,"Andrea");

        String json = mapper.writeValueAsString(andrea);

        given(service.findById(1)).willReturn(Optional.of(adreaUpdated));
        given(service.updateEntity(1,andrea)).willReturn(adreaUpdated);

        mvc.perform(put("/entities/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(adreaUpdated.getId())))
                .andExpect(jsonPath("$.entityName",is(adreaUpdated.getEntityName())));

        verify(service,times(1)).updateEntity(anyInt(),any(ModelEntityDTO.class));
        verify(service,times(1)).findById(anyInt());
    }

}
