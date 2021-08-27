package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    @ManyToMany (
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    private Set<Activity> userFavouriteActivities = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String password, String firstName, String lastName, String username) {

        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public void addFavouriteActivity(Activity activity) {
        userFavouriteActivities.add(activity);
        activity.getUsers().add(this);
    }

    public void removeFavouriteActivity(Activity activity) {
        userFavouriteActivities.remove(activity);
        activity.getUsers().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Activity> getUserFavouriteActivities() {
        return userFavouriteActivities;
    }

    public void setUserFavouriteActivities(Set<Activity> userFavouriteActivities) {
        this.userFavouriteActivities = userFavouriteActivities;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
