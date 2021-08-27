package com.mmd.MakeMyDay;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDayRepository extends CrudRepository<UserDay, Long> {
    List<UserDay> findByUser_Id(Long Id);
}
