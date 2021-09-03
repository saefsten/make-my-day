package com.mmd.MakeMyDay;

import javax.persistence.*;
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
    @Column (name="HOURS")
    private int hours;
    @Column (name="DESCRIPTION")
    private String description;
    @Column (name="URL")
    private String url;
    @Column (name="STREETNAME")
    private String streetName;
    @Column (name="STREETNUMBER")
    private String streetNumber;
    @Column (name="POSTALCODE")
    private String postalCode;
    @Column (name="CITY")
    private String city;
    @Column (name="LATITUDE")
    private String latitude;
    @Column (name="LONGITUDE")
    private String longitude;


    @Column (name="ACTIVITIES_IN_PACKAGES")
    @ManyToMany (mappedBy = "activities")
    private Set<Package> packages;

    @Column (name="CATEGORIES_IN_ACTIVITY")
    @ManyToMany
    private Set<Category> categories;

    @ManyToMany(mappedBy = "userFavouriteActivities",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "activity") // one activity can have many reviews, review only for one activity
    private Set<Review> reviews;


    public Activity() {
    }

    public Activity(String name, Set<Category> categories, Double price, int hours, String description, String url, String streetName, String streetNumber, String postalCode, String city, String latitude, String longitude, Set<Package> packages) {
        this.name = name;
        this.categories = categories;
        this.price = price;
        this.hours = hours;
        this.description = description;
        this.url = url;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.packages = packages;
    }

    public void addUser(User user) {
        users.add(user);
        user.getUserFavouriteActivities().add(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getUserFavouriteActivities().remove(this);
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.getActivities().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getActivities().remove(this);
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
