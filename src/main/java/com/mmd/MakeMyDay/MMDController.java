package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class MMDController {

    @Autowired
    ActivityService activityService;

    @Autowired
    UserRepository userRepository;

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
        List<Activity> activities = activityService.findAllActivities();
        model.addAttribute("activities", activities);
        return "activity/activities";
    }

    @GetMapping("/activity/create")
    String createActivity(Model model){
        model.addAttribute("activity", new Activity());
        return "activity/create";
    }

    @GetMapping("/activity/update")
    String updateActivity(Model model, @ModelAttribute Activity activity){
        model.addAttribute("activity", activity);
        return "activity/create";
    }


    @PostMapping("/activity/create")
    String saveActivity(Model model, @ModelAttribute Activity activity) {
        activityService.saveActivity(activity);
        String message = "Activity " + activity.getName() + " was saved!";
        model.addAttribute("message", message);
        return "activity/create";
    }

    @GetMapping("/activity/{id}")
    String activity(Model model, @PathVariable Long id){
//        Activity activity = (Activity) activityRepository.findById(id).orElse(null);
        Activity activity = activityService.findActivityById(id);
        model.addAttribute("activity", activity);
        return "activity/activityDetails";
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

    @GetMapping("/user/account")
    String account(HttpServletRequest request, Model model) {
        User user = userRepository.findByUsername(currentUserName(request));
        Set<Activity> userFavourites = user.getUserFavouriteActivities();
        model.addAttribute("favourites", userFavourites);
        return "user/account";
    }

    @GetMapping("/user/addfavourite")
    String addFavourite(HttpServletRequest request, @RequestParam Long id) {
        Activity activity = activityService.findActivityById(id);
        User user = userRepository.findByUsername(currentUserName(request));
        user.addFavouriteActivity(activity);
        activity.addUser(user);
        userRepository.save(user);
        activityService.saveActivity(activity);
        return "redirect:/activities";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

}
