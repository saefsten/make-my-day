package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="PACKAGE_NAME")
    private String packageName;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="START_TIMES")
    private String startTimes;

    @Column (name="PACKAGES_WITH_ACTIVITIES")
    @ManyToMany
    private Set<Activity> activities;

    public Package() {
    }

    public Package(String packageName, String description, Set<Activity> activities) {
        this.packageName = packageName;
        this.description = description;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public String getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }
}
