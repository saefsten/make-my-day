package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.time.Duration;
import java.util.HashSet;
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
    @Column (name="DURATION")
    private Duration approxDuration;
    @Column (name="DESCRIPTION")
    private String description;
    @Column (name="URL")
    private String url;
    @Column (name="ADDRESS")
    private String address;

    @Column (name="CATEGORIES_IN_ACTIVITY")
    @ManyToMany
    private Set<Category> categories;

    @ManyToMany(mappedBy = "userFavouriteActivities",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>();

    public Activity() {
    }

    public Activity(String name, Set<Category> categories, Double price, Duration approxDuration, String description, String url, String address) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getUserFavouriteActivities().add(this);
    }
}
