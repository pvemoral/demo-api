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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

import java.util.Arrays;
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
        this.entities = Collections.singletonList(new ModelEntity("Ester"));

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
        ModelEntity adrea_added = new ModelEntity(1,"Andrea");

        given(service.addEntity(andrea)).willReturn(adrea_added);
       String json = mapper.writeValueAsString(andrea);
       // String json = "{ \"entityName\" : \"Andrea Vilaro\" }";

        System.out.println("JSON " + json);
        mvc.perform(post("/entities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(adrea_added.getId())))
                .andExpect(jsonPath("$.entityName",is(adrea_added.getEntityName())));

      /*

      .content(asJsonString(user)))
            .andExpect(status().isConflict());
    verify(userService, times(1)).exists(user
       */
    }



}
