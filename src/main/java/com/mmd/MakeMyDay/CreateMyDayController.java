package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateMyDayController {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEventRepository userEventRepository;

    @Autowired
    UserDayRepository userDayRepository;

    MMDController mmdController = new MMDController();

    @Autowired
    ActivityService activityService;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/createMyDay")
    String createMyDay(Model model, @RequestParam(required = false) Long packageId, @RequestParam(required = false, defaultValue = "false") boolean hasSaved) {
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);

        if (packageId != null) {
            Package pkg = packageRepository.findById(packageId).orElse(null);
            if (pkg != null) {
                model.addAttribute("times", getStartTimesFromPackage(pkg));
                model.addAttribute("activitiesInPackage", activityService.findByPackageId(packageId));
            }
        }

        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories);

        String[] hours = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        model.addAttribute("hours", hours);

        model.addAttribute("hasSaved", hasSaved);

        return "createMyDay/createMyDay";
    }

    @PostMapping("/createMyDay")
    String onPost(HttpServletRequest request, @RequestParam String dayName, @RequestParam List<String> events, @RequestParam String date) throws ParseException {
        List <UserEvent> userEvents = new ArrayList<>();
        User user = userRepository.findByUsername(mmdController.currentUserName(request));
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        UserDay userDay = new UserDay();
        userDayRepository.save(userDay);

        for (int i=0; i<events.size(); i++) {
            String startTime = events.get(i).substring(0, 5);
            Long activityId = Long.parseLong(events.get(i).substring(6));
            Activity activity = activityRepository.findById(activityId).orElse(null);
            UserEvent userEvent = new UserEvent(userDay, activity, startTime);
            userEventRepository.save(userEvent);
            userEvents.add(userEvent);
        }

        userDay.setUserEvents(userEvents);
        userDay.setUser(user);
        userDay.setDate(parsedDate);
        userDay.setName(dayName);
        userDayRepository.save(userDay);

        return "redirect:/createMyDay?hasSaved=true";
    }

    public static List<String> getStartTimesFromPackage(Package pkg) {
        String timeString = pkg.getStartTimes();
        List<String> times = new ArrayList<>();
        while (timeString.length() > 0) {
            if (timeString.contains(";")) {
                times.add(timeString.substring(0, 5));
                timeString = timeString.substring(6);
            }
        }

        return times;
    }
}
