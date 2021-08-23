package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Activity> findAllActivities() {
        return (List<Activity>)activityRepository.findAll();
    }

    public Activity findActivityById(Long id) {
        Activity activity = activityRepository.findById(id).get();
        if (activity == null) {
            System.out.println("Activity not found");
            return null;
        }
        return activity;
    }

    public List<Activity> findActivityByCategory(String category) {
        List<Activity> activities = activityRepository.findByCategories_Category(category);
        return activities;
    }
}
