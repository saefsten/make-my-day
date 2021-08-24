package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

public class InitController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/init") //saves a hardcoded user in the database
    public String init(){
        // tries to find the user so that we only save the user if it does not exist
        User user = userRepository.findByEmail("email");
        if (user == null) {
            user = new User();
            user.setEmail("email");
            user.setPassword(encoder.encode("123"));
            // svae the user with username user and an encoded value for 123 as password
            userRepository.save(user);
        }

        return "ok";
    }
}
