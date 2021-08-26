package com.mmd.MakeMyDay;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends CrudRepository <Activity, Long> {
    List<Activity> findByCategories_CategoryName(String categoryName);
    List<Activity> findByUsers_Username(String username);
}
