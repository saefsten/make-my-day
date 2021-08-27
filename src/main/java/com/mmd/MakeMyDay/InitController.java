package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

@RestController
public class InitController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ActivityService activityService;

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
