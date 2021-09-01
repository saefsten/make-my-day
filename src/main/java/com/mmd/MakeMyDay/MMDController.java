package com.mmd.MakeMyDay;

/*
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
*/
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MMDController {

    @Autowired
    PackageService packageService;

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @Autowired
    UserDayService userDayService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/")
    String index(Model model){
        List <Activity> activities = activityService.findAllActivities();
        model.addAttribute("activities", activities);
        return "start";
    }

    @GetMapping("/start")
    String start(Model model){
        List <Activity> activities = activityService.findAllActivities();
        model.addAttribute("activities", activities);
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
        Package pack = packageService.findPackageById(id);
        model.addAttribute("package", pacAct);
        model.addAttribute("packageId", id);
        model.addAttribute("pack", pack);
        return "package/packageDetails"; //redirect to create my day, which in turn will display the create my day schedule
    }

    @GetMapping("/activities")
    String activities(Model model, HttpServletRequest request){
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        List<Activity> activities = activityService.findAllActivities();
        model.addAttribute("activities", activities);
        try {
            User user = userService.findUserByUsername(currentUserName(request));
        } catch (NullPointerException ne) {
            ne.getStackTrace();
            List<Long> userFavouritesActivityId = new ArrayList<>();
            model.addAttribute("userFavourites", userFavouritesActivityId);
            return "activity/activities";
        }
        User user = userService.findUserByUsername(currentUserName(request));
        List<Long> userFavouritesActivityId = getUserFavouritesId(user);
        model.addAttribute("userFavourites", userFavouritesActivityId);
        return "activity/activities";
    }

    @GetMapping("/activity/{id}")
    String activity(Model model, HttpServletRequest request, @PathVariable Long id){
        Activity activity = activityService.findActivityById(id);
        model.addAttribute("activity", activity);
        Set<Review> reviews = activity.getReviews();
        model.addAttribute("reviews", reviews);
        try {
            User user = userService.findUserByUsername(currentUserName(request));
        } catch (NullPointerException ne) {
            ne.getStackTrace();
            List<Long> userFavouritesActivityId = new ArrayList<>();
            model.addAttribute("userFavourites", userFavouritesActivityId);
            return "activity/activityDetails";
        }
        User user = userService.findUserByUsername(currentUserName(request));
        List<Long> userFavouritesActivityId = getUserFavouritesId(user);
        model.addAttribute("userFavourites", userFavouritesActivityId);
        boolean userReviewed = false;
        for (Review review : reviews) {
            if (review.getUser().getId() == user.getId()) {
                userReviewed = true;
            }
        }
        model.addAttribute("activityReviewedByUser", userReviewed);
        return "activity/activityDetails";
    }


    @GetMapping("/review/{id}")
    public String writeReview(HttpServletRequest request, Model model, @PathVariable Long id) {
        Activity activity = activityService.findActivityById(id);
        User user = userService.findUserByUsername(currentUserName(request));
        Set<Review> reviewsForActivity =  activity.getReviews();
        for (Review rew : reviewsForActivity) {
            if (rew.getUser().getId() == user.getId()) {
                return "redirect:/activity/"+id;
            }
        }
        model.addAttribute("activityName", activity.getName());
        model.addAttribute("activityId", id);
        Review review = new Review();
        model.addAttribute("review", review);
        return "activity/review";
    }

    @PostMapping("saveReview")
    public String saveReview(HttpServletRequest request, Model model, @ModelAttribute Review review, @RequestParam Long activityId) {
        Activity activity = activityService.findActivityById(activityId);
        User user = userService.findUserByUsername(currentUserName(request));
        review.setUser(user);
        review.setActivity(activity);
        review.setDate(LocalDate.now());
        reviewService.saveReview(review);
        return "redirect:/activity/"+activityId;
    }

    @GetMapping("/mydays")
    String myDays(HttpServletRequest request, Model model, @RequestParam (required=false) Long userDayId) {
        User user = userService.findUserByUsername(currentUserName(request));
        List <UserDay> userDays = userDayService.findUserDayByUser(user);
        List <UserDay> pastUserDays = new ArrayList<>();
        List <UserDay> comingUserDays = new ArrayList<>();
        List <UserDay> ongoingUserDays = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        int dateDiff;

        for (UserDay userDay : userDays) {
            dateDiff = currentDate.compareTo(userDay.getDate());
            if (dateDiff > 0){
                pastUserDays.add(userDay);
            } else if (dateDiff < 0){
                comingUserDays.add(userDay);
            } else {
                ongoingUserDays.add(userDay);
            }
        }
        model.addAttribute("pastUserDays", pastUserDays);
        model.addAttribute("comingUserDays", comingUserDays);
        model.addAttribute("ongoingUserDays", ongoingUserDays);

        if (userDayId != null) {
            UserDay chosenUserDay = userDayService.findUserDayById(userDayId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateString = chosenUserDay.getDate().format(formatter);
            String[] hours = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};

            model.addAttribute("chosenUserDay", chosenUserDay);
            model.addAttribute("chosenDate", dateString);
            model.addAttribute("chosenName", chosenUserDay.getName());
            model.addAttribute("chosenTitle", chosenUserDay.getName() + " (" + dateString + ")");
            model.addAttribute("hours", hours);
            model.addAttribute("userEvents", chosenUserDay.getUserEvents());
            model.addAttribute("startTimes", getUserDayStartTimes(chosenUserDay));
        }

        return "user/mydays";
    }

    @GetMapping("/user/account")
    String account(HttpServletRequest request, Model model) {
        User user = userService.findUserByUsername(currentUserName(request));
        Set<Activity> userFavourites = user.getUserFavouriteActivities();
        model.addAttribute("favourites", userFavourites);
        List<Long> userFavouritesActivityId = getUserFavouritesId(user);
        model.addAttribute("userFavourites", userFavouritesActivityId);
        return "user/account";
    }

    @GetMapping("/user/addfavourite")
    String addFavourite(HttpServletRequest request, @RequestParam Long id, @RequestParam(required = false) String previousURL) {
        Activity activity = activityService.findActivityById(id);
        User user = userService.findUserByUsername(currentUserName(request));
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
        return "redirect:/"+previousURL;

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

    public List <String> getUserDayStartTimes (UserDay userDay) {
        List <UserEvent> userEvents = userDay.getUserEvents();
        List <String> startTimes = new ArrayList<>();
        for (UserEvent userEvent : userEvents) {
            startTimes.add(userEvent.getStartTime());
        }
        return startTimes;
    }

}
