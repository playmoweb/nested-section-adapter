package com.example.nestedadapterexample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thibaud on 22/06/2017.
 */

public class Movie {
    private String name;
    private String type;
    private Date year;

    private List<Actor> actors = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    public Movie(String name, String type, Date year) {
        this.name = name;
        this.type = type;
        this.year = year;
    }

    public Movie addActor(Actor actor) {
        actors.add(actor);
        return this;
    }

    public Movie addReward(Reward reward) {
        rewards.add(reward);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}
