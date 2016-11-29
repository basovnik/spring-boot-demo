package com.github.basovnik.demo.dal;

import com.github.basovnik.demo.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserDao {

    private Datastore datastore;

    public UserDao(@Qualifier("usersDatasource") Datastore datastore) {
        this.datastore = datastore;
    }

    public List<User> getAll() {
        final Query<User> query = datastore.createQuery(User.class);
        return query.asList();
    }

    public User get(String id) {
        return datastore.get(User.class, toObjectId(id));
    }

    public void create(User user) {
        datastore.save(user);
    }

    public void delete(String id) {
        datastore.delete(User.class, toObjectId(id));
    }

    private ObjectId toObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            log.debug("ID={} is not valid ObjectId representation", id, e);
        }
        return null;
    }
}
