package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    private int id;
    @Column(name="CATEGORYNAME")
    private String categoryName;

    @Column (name="ACTIVITIES_WITH_CATEGORY")
    @ManyToMany (mappedBy = "categories")
    private Set<Activity> activities;

    public Category() {

    }

    public Category(int id, String categoryName, Set<Activity> activities) {
        this.id = id;
        this.categoryName = categoryName;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}