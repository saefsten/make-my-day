package com.mmd.MakeMyDay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MMDController {

    @GetMapping("/")
    String index(){
        return "start";
    }
}