package com.mmd.MakeMyDay;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="RATING")
    private int rating;
    @Column(name="REVIEWTEXT")
    private String reviewText;
    @Column(name="DATE")
    private LocalDate date;

    @ManyToOne // one review by one user, one user can write many reviews
    private User user;

    @ManyToOne // one review for one activity, one activity can have many reviews
    private Activity activity;

    public Review() {

    }

    public Review(int rating, String reviewText, User user, Activity activity) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.user = user;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
