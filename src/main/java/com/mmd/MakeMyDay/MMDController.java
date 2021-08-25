package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MMDController {


    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivityService activityService;

    @GetMapping("/")
    String index(){
        return "start";
    }

    @GetMapping("/start")
    String start(){
        return "start";
    }

    @GetMapping("/packages")
    String packages(){
        return "package/packages";
    }

    @GetMapping("/activities")
    String activities(Model model){
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);
        return "activity/activities";
    }

    @GetMapping("/activity/{id}")
    String activity(Model model, @PathVariable Long id){
        Activity activity = (Activity) activityRepository.findById(id).orElse(null);
        model.addAttribute("activity", activity);
        return "activity/activityDetails";
    }

    @GetMapping("/createMyDay")
    String createMyDay(Model model){
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);
        return "createMyDay/createMyDay";
    }

/*
    @PostMapping("/createMyDay")
    String createMyDayPost(Model model, @RequestParam String category){
        System.out.println(category);
        List<Activity> activities = (List<Activity>) activityRepository.findByCategories_Category(category.toUpperCase());
        model.addAttribute("activities", activities);
        return "createMyDay/createMyDay";
    }
*/

}
