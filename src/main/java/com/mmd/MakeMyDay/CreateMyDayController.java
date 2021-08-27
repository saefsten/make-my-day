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
import java.util.Objects;

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
    PackageService packageService;

    @GetMapping("/createMyDay")
    String createMyDay(Model model, @RequestParam(required = false) Long packageId) {
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);

        if (packageId != null) {
            model.addAttribute("activitiesInPackage", activityService.findByPackageId(packageId));
            List<Activity> some = activityService.findByPackageId(packageId);
            List<Package> packages = packageService.findAllPackages();
            for (Package p : packages) {
                if (Objects.equals(p.getId(), packageId)) {
                    String timeString = p.getStartTimes();
                    List<String> times = new ArrayList<>();
                    while (timeString.length() > 0) {
                        if (timeString.contains(";")) {
                            times.add(timeString.substring(0, 5));
                            timeString = timeString.substring(6);
                        }
                    }
                    model.addAttribute("times", times);
                    break;
                }
            }
        }

        String hours[] = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        model.addAttribute("hours", hours);

        return "createMyDay/createMyDay";
    }

    @PostMapping("/createMyDay")
    String onPost(HttpServletRequest request,@RequestParam List<String> events, @RequestParam String date) throws ParseException {
        List <UserEvent> userEvents = new ArrayList<>();
        User user = userRepository.findByUsername(mmdController.currentUserName(request));
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        UserDay userDay = new UserDay();
        userDayRepository.save(userDay);

        for (int i=0; i<events.size(); i++) {
            Integer startTime = Integer.parseInt(events.get(0).substring(0, 2));
            Long activityId = Long.parseLong(events.get(0).substring(6));
            Activity activity = (Activity) activityRepository.findById(activityId).orElse(null);
            UserEvent userEvent = new UserEvent(userDay, activity, startTime);
            userEventRepository.save(userEvent);
            userEvents.add(userEvent);
        }

        userDay.setUserEvents(userEvents);
        userDay.setUser(user);
        userDay.setDate(parsedDate);
        userDayRepository.save(userDay);

        return "redirect:/createMyDay";
    }
}
