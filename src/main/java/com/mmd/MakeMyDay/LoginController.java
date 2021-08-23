package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

   @PostMapping("/register")
    public String registerNewUser(HttpSession session, @RequestParam String firstName, @RequestParam  String lastName, @RequestParam String email, @RequestParam String password){
        User user = new User(firstName, lastName, email, password);
        userRepository.save(user);
        return "start";
    }

}
