package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categories {
    @Id
    private int id;
    @Column(name="CATEGORY")
    private String category;

    @ManyToMany
    private Set<Activity> activities;

    public Categories() {

    }

    public Categories(int id, String category, Set<Activity> activities) {
        this.id = id;
        this.category = category;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
