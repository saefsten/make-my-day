package com.mmd.MakeMyDay;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository <Category, Integer> {
    List<Category> findByActivities_Id(Long id);


}
