package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findUSerByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) { userRepository.delete(user); }
}
