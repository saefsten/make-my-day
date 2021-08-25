package com.mmd.MakeMyDay;

import javax.persistence.*;

@Entity
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserDay userDay;
    @ManyToOne
    private Activity activity;
    private Integer startTime;

    public UserEvent() {
    }

    public UserEvent(UserDay userDay, Activity activity, Integer startTime) {
        this.userDay = userDay;
        this.activity = activity;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDay getUserDay() {
        return userDay;
    }

    public void setUserDay(UserDay userDay) {
        this.userDay = userDay;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }
}
