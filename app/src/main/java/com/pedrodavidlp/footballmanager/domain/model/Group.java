package com.pedrodavidlp.footballmanager.domain.model;

import java.util.List;

public class Group {
    private String id;
    private String password;

    public Group() {

    }

    public Group(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
