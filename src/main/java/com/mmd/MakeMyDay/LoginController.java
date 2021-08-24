package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/login")
    public String login(){

        return "user/login";
    }

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

   @PostMapping("/register")
    public String registerNewUser(Model model, HttpSession session, @RequestParam String firstName, @RequestParam  String lastName, @RequestParam String email, @RequestParam String password, @RequestParam String username){
       User newUser = userRepository.findByUsername(username);
       model.addAttribute("username", username);
       if(newUser == null){
           User user = new User(encoder.encode(password), firstName, lastName, email, username);
           userRepository.save(user);
           return "start";
       }
        else{
            model.addAttribute("message", "You already have an account.");
            return "user/register";

       }
    }

}
