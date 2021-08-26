package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

    @GetMapping("/createMyDay")
    String createMyDay(Model model){
        List<Activity> activities = (List<Activity>) activityRepository.findAll();
        model.addAttribute("activities", activities);

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
