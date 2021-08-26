package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class CreateMyDayController {

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/createMyDay")
    String createMyDay(Model model){
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);

        String hours[] = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        model.addAttribute("hours", hours);

        return "createMyDay/createMyDay";
    }

    @PostMapping("/createMyDay")
    String onPost(@RequestParam List<String> events, @RequestParam String date) {
        System.out.println(events);
        System.out.println(date);
        int h = Integer.parseInt(events.get(0).substring(0, 2));
        Long id = Long.parseLong(events.get(0).substring(6));
        System.out.println("Int value:" + h +" Id:" + id);

        return "redirect:/createMyDay";
    }
}
