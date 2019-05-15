package com.jk.model;

import java.io.Serializable;

public class TypeBean implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
