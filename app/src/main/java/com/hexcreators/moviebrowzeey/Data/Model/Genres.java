package com.hexcreators.moviebrowzeey.Data.Model;

import io.realm.RealmObject;

public class Genres  extends RealmObject {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
