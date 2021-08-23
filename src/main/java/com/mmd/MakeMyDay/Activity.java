package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="ID")
    private Long id;
    @Column (name="NAME")
    private String name;
    @Column (name="CATEGORIES")
//    private List<ActivityCategory> categories = new ArrayList<>();
    @OneToMany
    private List<String> categories;
    @Column (name="PRICE")
    private Double price;
    @Column (name="DURATION")
    private Duration approxDuration;
    @Column (name="DESCRIPTION")
    private String description;
    @Column (name="URL")
    private String url;
    @Column (name="ADDRESS")
    private String address;

    public Activity() {
    }

    public Activity(String name, List<String> categories, Double price, Duration approxDuration, String description, String url, String address) {
        this.name = name;
        this.categories = categories;
        this.price = price;
        this.approxDuration = approxDuration;
        this.description = description;
        this.url = url;
        this.address = address;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Duration getApproxDuration() {
        return approxDuration;
    }

    public void setApproxDuration(Duration approxDuration) {
        this.approxDuration = approxDuration;
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
}
