package com.mmd.MakeMyDay;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //private List<ActivityCategory> categories = new ArrayList<>();
    private Double price;
    private Duration approxDuration;
    private String description;
    private String url;
    private String address;

    public Activity() {
    }

    public Activity(String name, /*List<ActivityCategory> categories,*/ Double price, Duration approxDuration, String description, String url, String address) {
        this.name = name;
        //this.categories = categories;
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

    /*public List<ActivityCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ActivityCategory> categories) {
        this.categories = categories;
    }*/

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
