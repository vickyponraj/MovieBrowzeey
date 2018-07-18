package com.hexcreators.moviebrowzeey.Data.Model;

import io.realm.RealmObject;

public class Dates extends RealmObject {

    private String minimum;

    private String maximum;

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }
}
