package com.mmd.MakeMyDay;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByUser_Id(Long id);
}
