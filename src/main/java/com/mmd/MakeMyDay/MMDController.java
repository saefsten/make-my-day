package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    CategoryService categoryService;

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
        model.addAttribute("packageId", id);
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
        List<String> catIds = new ArrayList<>();
        model.addAttribute("catIds", catIds);
        return "activity/create";
    }

    @GetMapping("/activity/update")
    String updateActivity(Model model, @RequestParam Long id){
        Activity activity = activityService.findActivityById(id);
        List<Category> categories = categoryService.findAllCategoriesByActivity(id);
        List<String> catIds = new ArrayList<>();
        for (Category category : categories) {
            catIds.add(String.valueOf(category.getId()));
        }
        model.addAttribute("activity", activity);
        model.addAttribute("catIds", catIds);
        return "activity/create";
    }

    @PostMapping("/activity/create")
    String saveActivity(Model model, @ModelAttribute Activity activity, @RequestParam("category") List<Integer> categories) {
        Set<Category> currentCategoriesInActivity = activity.getCategories();
        Set<Category> categoriesInActivity = new HashSet<>();
        // make the checked boxes from html to a list of categories: categoriesInActivity
        if (categories != null) {
            for (int cat : categories) {
                categoriesInActivity.add(categoryService.findCategoryById(cat));
            }
        }
        if (currentCategoriesInActivity == null) { // if no previous entries for this activity, just take the list
            activity.setCategories(categoriesInActivity);
            for (Category category : categoriesInActivity) { // for each category also save the activity in the activities list of the category
                Category newCategory = categoryService.findCategoryById(category.getId());
                newCategory.addActivity(activity);
                categoryService.saveCategory(newCategory);
            }
        } else {
            currentCategoriesInActivity.addAll(categoriesInActivity); // add all entries from the form to the total list: currentCategoriesInActivity
            for (Category category : currentCategoriesInActivity) {
                if (!categoriesInActivity.contains(category)) { // if the category in current list is not in that from the html, remove it
                    currentCategoriesInActivity.remove(category);
                    Category removedCategory = categoryService.findCategoryById(category.getId());
                    removedCategory.removeActivity(activity); // also remove this activity from the activities list of the category
                    categoryService.saveCategory(removedCategory);
                }
            }
            activity.setCategories(currentCategoriesInActivity);
        }
        activityService.saveActivity(activity);
        return "redirect:/activity/"+activity.getId();
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
