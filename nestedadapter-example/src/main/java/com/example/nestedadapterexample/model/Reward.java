package com.example.nestedadapterexample.model;

import java.util.Date;

/**
 * Created by thibaud on 22/06/2017.
 */

public class Reward {
    private String name;
    private Date obtained;

    public Reward(String name, Date obtained) {
        this.name = name;
        this.obtained = obtained;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getObtained() {
        return obtained;
    }

    public void setObtained(Date obtained) {
        this.obtained = obtained;
    }
}
