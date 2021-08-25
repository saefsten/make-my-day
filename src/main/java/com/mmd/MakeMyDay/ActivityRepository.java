package com.mmd.MakeMyDay;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository <Activity, Long> {
    List<Activity> findByCategories_CategoryName(String categoryName);
}
