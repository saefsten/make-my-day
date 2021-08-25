package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private Date date;
    @OneToMany(mappedBy = "userDay", cascade = CascadeType.ALL)
    private List<UserEvent> userEvents;

    public UserDay() {
    }

    public UserDay(User user, Date date, List<UserEvent> userEvents) {
        this.user = user;
        this.date = date;
        this.userEvents = userEvents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }
}
