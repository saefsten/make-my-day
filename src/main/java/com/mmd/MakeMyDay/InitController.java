package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/init") //saves a hardcoded user in the database
    public String init(){
        User user = userRepository.findByUsername("username");
        if (user == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("123"));
            userRepository.save(user);
        }

        return "ok";
    }
}
