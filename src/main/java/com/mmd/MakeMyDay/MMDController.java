package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MMDController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/")
    String index(){
        return "start";
    }

    @GetMapping("/anders")
    String anders(Model model){
        List<Activity> activities = activityService.findActivityByCategory("CUISINE");
        model.addAttribute("alist", activities);
        return "anders";
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
