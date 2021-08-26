package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="ID")
    private Long id;
    @Column (name="NAME")
    private String name;
    @Column (name="PRICE")
    private Double price;
    @Column (name="HOURS")
    private int hours;
    @Column (name="DESCRIPTION")
    private String description;
    @Column (name="URL")
    private String url;
    @Column (name="ADDRESS")
    private String address;

    @Column (name="ACTIVITIES_IN_PACKAGES")
    @ManyToMany (mappedBy = "activities")
    private Set<Package> packages;

    @Column (name="CATEGORIES_IN_ACTIVITY")
    @ManyToMany
    private Set<Category> categories;

    public Activity() {
    }

    public Activity(String name, Set<Category> categories, Double price, int hours, String description, String url, String address, Set<Package> packages) {
        this.name = name;
        this.categories = categories;
        this.price = price;
        this.hours = hours;
        this.description = description;
        this.url = url;
        this.address = address;
        this.packages = packages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }
}
