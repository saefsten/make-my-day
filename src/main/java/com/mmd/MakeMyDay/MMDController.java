package com.mmd.MakeMyDay;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MMDController {


    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    PackageService packageService;

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
    String packages(Model model){
        List<Package> packages = packageService.findAllPackages();
        model.addAttribute("packages", packages);
        return "package/packages";
    }

    @GetMapping("/package/{id}")
    String pacAct(Model model, @PathVariable Long id) {
        List<Activity> pacAct = (List<Activity>) activityRepository.findByPackages_Id(id);
        model.addAttribute("package", pacAct);
        return "package/packageDetails"; //redirect to create my day, which in turn will display the create my day schedule
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
    String createMyDay(){
        return "createMyDay/createMyDay";
    }

}
