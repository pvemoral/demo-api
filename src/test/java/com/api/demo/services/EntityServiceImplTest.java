package com.api.demo.services;

import com.api.demo.models.ModelEntity;
import com.api.demo.models.ModelEntityDTO;
import com.api.demo.repositories.EntityPersistence;
import com.api.demo.repositories.EntityPersistenceImpl;
import com.api.demo.repositories.ModelEntityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityServiceImplTest {
    @Autowired
    private ModelEntityRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    private EntityService entityService;

    private EntityPersistence entityPersistence;

    @Before
    public void setUp() throws Exception {
        this.repository.save(new ModelEntity(1,"Ester"));

        this.entityPersistence = new EntityPersistenceImpl(this.repository);
        this.entityService = new EntityServiceImpl(this.entityPersistence);
    }

    @Test
    public void whenFindAllEntities_AndWeHaveMoreThanOneEntities_thenReturnEntities() {
        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all);
        assertNotNull(all.get(0).getId());

    }

    @Test
    public void whenAddEntity_thenHaveOneMoreEntitiesThanBefore() {
        List<ModelEntity> all_before = this.entityService.findAll();

        this.entityService.addEntity(new ModelEntityDTO("Andrea"));

        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all_before);
        assertNotNull(all);
        assertNotEquals(all_before.size(),all.size());
        assertEquals(all_before.size() + 1, all.size());

    }

    @Test
    public void whenFindByIdExistEntity_thenGetEntity() {
        Optional<ModelEntity> ester = this.entityService.findById(1);

        assertNotNull(ester);
        assertEquals(ester.get().getEntityName(),"Ester");
        assertEquals(ester.get().getId().toString(),"1");
    }

    @Test
    public void whenFindByIdNonExistEntity_thenGetEntity() {

        Optional<ModelEntity> ester = this.entityService.findById(4);

        assertEquals(ester,Optional.empty());

    }

    @Test
    public void whenDeleteExistEntity_thenWeHaveOneLessEntitiesThanBefore() {

        List<ModelEntity> all_before = this.entityService.findAll();

        this.entityService.deleteEntity(2);

        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all_before);
        assertNotNull(all);
        assertNotEquals(all_before.size(),all.size());
        assertEquals(all_before.size() , all.size() + 1);
    }

    @Test
    public void whenDeleteNonExistEntity_thenWeHaveTheSamesEntitiesThanBefore() {

        List<ModelEntity> all_before = this.entityService.findAll();

        this.entityService.deleteEntity(14);

        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all_before);
        assertNotNull(all);
        assertEquals(all_before.size(),all.size());
    }


    @Test
    public void whenDeleteExistEntity_thenFindByIdReturnEmpty() {
        this.entityService.deleteEntity(1);

        Optional<ModelEntity> ester = this.entityService.findById(1);

        assertEquals(ester,Optional.empty());

    }

    @Test
    public void whenDeleteNonExistEntity_thenFindByIdReturnEmpty() {
        this.entityService.deleteEntity(10);

        Optional<ModelEntity> ester = this.entityService.findById(10);

        assertEquals(ester,Optional.empty());

    }

    @Test
    public void whenUpdatedExistEntity_thenFindByIdReturnUpdatedEntity() {

        this.entityService.updateEntity(1, new ModelEntityDTO("Andrea"));

        Optional<ModelEntity> ester = this.entityService.findById(1);

        assertEquals(ester.get().getEntityName(),"Andrea");

    }

    @Test
    public void whenUpdatedNonExistEntity_thenFindByIdReturnEmpty() {
        this.entityService.updateEntity(10, new ModelEntityDTO("Andrea") );

        Optional<ModelEntity> ester = this.entityService.findById(10);

        assertEquals(ester,Optional.empty());

    }


}