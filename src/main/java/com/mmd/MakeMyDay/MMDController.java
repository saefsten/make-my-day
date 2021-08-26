package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class MMDController {

    @Autowired
    PackageService packageService;

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

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
        List<Activity> pacAct = (List<Activity>) activityService.findByPackageId(id);
        model.addAttribute("package", pacAct);
        return "package/packageDetails"; //redirect to create my day, which in turn will display the create my day schedule
    }

    @GetMapping("/activities")
    String activities(Model model, HttpServletRequest request){
        List<Activity> activities = activityService.findAllActivities();
        model.addAttribute("activities", activities);
        User user = userService.findUSerByUsername(currentUserName(request));
        List<Long> userFavouritesActivityId = getUserFavouritesId(user);
        model.addAttribute("userFavourites", userFavouritesActivityId);
        return "activity/activities";
    }

    @GetMapping("/activity/create")
    String createActivity(Model model){
        model.addAttribute("activity", new Activity());
        return "activity/create";
    }

    @GetMapping("/activity/update")
    String updateActivity(Model model, @RequestParam Long id){
        Activity activity = activityService.findActivityById(id);
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
        Activity activity = activityService.findActivityById(id);
        model.addAttribute("activity", activity);
        return "activity/activityDetails";
    }

    @GetMapping("/user/account")
    String account(HttpServletRequest request, Model model) {
        User user = userService.findUSerByUsername(currentUserName(request));
        Set<Activity> userFavourites = user.getUserFavouriteActivities();
        model.addAttribute("favourites", userFavourites);
        List<Long> userFavouritesActivityId = getUserFavouritesId(user);
        model.addAttribute("userFavourites", userFavouritesActivityId);
        return "user/account";
    }

    @GetMapping("/user/addfavourite")
    String addFavourite(HttpServletRequest request, @RequestParam Long id) {
        Activity activity = activityService.findActivityById(id);
        User user = userService.findUSerByUsername(currentUserName(request));
        Set<Activity> userFavActsId = user.getUserFavouriteActivities();
        if (userFavActsId.contains(activity)) {
            user.removeFavouriteActivity(activity);
            activity.removeUser(user);
        } else {
            user.addFavouriteActivity(activity);
            activity.addUser(user);
        }
        userService.saveUser(user);
        activityService.saveActivity(activity);
        return "redirect:/activities";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    public List<Long> getUserFavouritesId(User user) {
        List<Activity> userFavourites = activityService.findActivityByUser(user.getUsername());
        List<Long> userFavouritesActivityId = new ArrayList<>();
        for (Activity activity : userFavourites) {
            userFavouritesActivityId.add(activity.getId());
        }
        return userFavouritesActivityId;
    }

}
