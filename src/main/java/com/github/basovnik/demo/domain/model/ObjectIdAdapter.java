package com.github.basovnik.demo.domain.model;

import org.bson.types.ObjectId;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ObjectIdAdapter extends XmlAdapter<String, ObjectId> {

    @Override
    public ObjectId unmarshal(String str) throws Exception {
        return new ObjectId(str);
    }

    @Override
    public String marshal(ObjectId o) throws Exception {
        return o.toHexString();
    }
}
