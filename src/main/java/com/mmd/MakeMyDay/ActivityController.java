package com.mmd.MakeMyDay;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    Google google;

    @Bean
    public Google google() {
        return new Google();
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/createActivity")
    String createActivity(Model model){
        model.addAttribute("activity", new Activity());
        List<String> catIds = new ArrayList<>();
        model.addAttribute("catIds", catIds);
        return "activity/create";
    }

    @GetMapping("/updateActivity")
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

    @PostMapping("/createActivity")
    String saveActivity(Model model, @ModelAttribute Activity activity, @RequestParam(value = "category") List<Integer> categories) {
//        if (categories.isEmpty()) {
//            model.addAttribute("message", "choose at least one category");
//            model.addAttribute("activity", activity);
//            return "activity/create";
//        }
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
        Map<String, Double> coordinates = location(activity.getStreetName(), activity.getStreetNumber(), activity.getPostalCode(), activity.getCity());
        activity.setLatitude(coordinates.get("lat").toString());
        activity.setLongitude(coordinates.get("lng").toString());
        activityService.saveActivity(activity);
        return "redirect:/activity/"+activity.getId();
    }


    public Map location(String streetName, String streetNumber, String postalCode, String city) {
        String loc = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address="+streetName+"+"+streetNumber+"+"+postalCode+"+"+city+"&key="+google.API_KEY, String.class);
        Map<String, Object> map = new Gson()
                .fromJson(loc, new TypeToken<HashMap<String, Object>>() {
                }.getType());
        List<Map> results = getNestedValue(map,"results");
        Map map1 = results.get(0);
        Map<String, Object> geometry = getNestedValue(map1, "geometry");
        Map<String, Double> coordinates = (Map)geometry.get("location");
        return coordinates;
    }

    public static <T> T getNestedValue(Map map, String... keys) {
        Object value = map;

        for (String key : keys) {
            value = ((Map) value).get(key);
        }

        return (T) value;
    }
}
