package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDayService {

    @Autowired
    UserDayRepository userDayRepository;

    public List<UserDay> findUserDayByUser (User user) {
        return userDayRepository.findByUser_Id(user.getId());
    }

    public UserDay findUserDayById (Long id) {
        return userDayRepository.findById(id).get();
    }
}
