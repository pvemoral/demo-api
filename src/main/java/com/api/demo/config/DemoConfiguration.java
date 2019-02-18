package com.api.demo.config;

import com.api.demo.repositories.EntityPersistence;
import com.api.demo.repositories.EntityPersistenceImpl;
import com.api.demo.repositories.ModelEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

    @Bean
    EntityPersistence entityPersistence(ModelEntityRepository repository) {
        return new EntityPersistenceImpl(repository);
    }

}
