package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public List<Activity> findAllActivities() {
        return (List<Activity>) activityRepository.findAll();
    }

    public Activity findActivityById(Long id) {
        return activityRepository.findById(id).get();
    }

    public List<Activity> findActivityByCategory(String category) {
        List<Activity> activities = activityRepository.findByCategories_CategoryName(category);
        return activities;
    }

    public List<Activity> findActivityByUser(String username) {
        List<Activity> activities = activityRepository.findByUsers_Username(username);
        return activities;
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public void removeActivity(Activity activity) { activityRepository.delete(activity); }

}
