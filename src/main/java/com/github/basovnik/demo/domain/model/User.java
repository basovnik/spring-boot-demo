package com.github.basovnik.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(value = "users", noClassnameStored = true)
public class User {

    @Id
    @XmlJavaTypeAdapter(ObjectIdAdapter.class)
    private ObjectId id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
}
