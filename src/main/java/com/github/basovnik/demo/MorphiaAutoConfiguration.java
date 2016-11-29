package com.github.basovnik.demo;

import com.github.basovnik.demo.domain.model.User;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaAutoConfiguration {

    @Autowired
    private MongoClient mongoClient;

    @Value("${demo.db.name}")
    private String databaseName;

    @Bean
    public Datastore usersDatasource() throws ClassNotFoundException {
        final Morphia morphia = new Morphia();
        morphia.mapPackage(User.class.getPackage().getName());
        return morphia.createDatastore(mongoClient, databaseName);
    }
}
