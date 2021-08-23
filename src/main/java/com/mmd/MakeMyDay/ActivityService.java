package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> findAllActivities() {
        return (List<Activity>)activityRepository.findAll();
    }

    public Activity findActivityById(Long id) {
        return activityRepository.findById(id).get();
    }

    public List<Activity> findActivityByCategory(String category) {
        List<Activity> activities = activityRepository.findByCategories_Category(category);
        return activities;
    }
}
