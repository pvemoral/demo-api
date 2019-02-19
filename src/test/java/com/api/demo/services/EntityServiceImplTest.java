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
        this.entityManager.persist(new ModelEntity("Ester"));

        this.entityPersistence = new EntityPersistenceImpl(this.repository);
        this.entityService = new EntityServiceImpl(this.entityPersistence);
    }

    @Test
    public void whenFindAllEntities_AndWeHaveMoreThanOneElement_thenReturnEntities() {
        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all);
        assertNotNull(all.get(0).getId());

    }

    @Test
    public void whenFindAddEntity_thenHaveOneMoreElementThanBefore() {
        List<ModelEntity> all_before = this.entityService.findAll();

        this.entityService.addEntity(new ModelEntityDTO("Andrea"));

        List<ModelEntity> all = this.entityService.findAll();

        assertNotNull(all_before);
        assertNotNull(all);
        assertNotEquals(all_before.size(),all.size());
        assertEquals(all_before.size() + 1, all.size());

    }
}