package com.mmd.MakeMyDay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MMDController {

    @GetMapping("/")
    String index(){
        return "start";
    }

    @GetMapping("/packages")
    String packages(){
        return "package/packages";
    }

    @GetMapping("/activities")
    String activities(){
        return "activity/activities";
    }

    @GetMapping("/createMyDay")
    String createMyDay(){
        return "createMyDay/createMyDay";
    }

}
