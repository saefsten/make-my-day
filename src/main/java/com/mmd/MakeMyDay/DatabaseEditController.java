package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatabaseEditController {
    @Autowired
    private DataSource dataSource;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ActivityService activityService;

    @Autowired
    PackageService packageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDayRepository userDayRepository;

    @Autowired
    UserEventRepository userEventRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/dbedit")
    public String dbedit(Model model) {
        populateModel(model);

        return "database/database";
    }

    @PostMapping("/dbedit")
    public String postDbedit(Model model, @RequestParam String query) {
        Integer rowsEffected = 0;
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query + ";")) {
            rowsEffected = stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            model.addAttribute("SQLFail", e.getMessage());
        }

        model.addAttribute("rowsEffected", rowsEffected);
        populateModel(model);

        return "database/database";
    }

    private void populateModel(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("activities", activityService.findAllActivities());
        model.addAttribute("packages", packageService.findAllPackages());
        model.addAttribute("users", (List<User>) userRepository.findAll());
        model.addAttribute("roles", (List<Role>) roleRepository.findAll());
        model.addAttribute("userDays", (List<UserDay>) userDayRepository.findAll());
        model.addAttribute("userEvents", (List<UserEvent>) userEventRepository.findAll());
        model.addAttribute("reviews", (List<Review>) reviewRepository.findAll());

        List<String[]> activityCategories = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ACTIVITY_CATEGORIES")) {
            while (rs.next()) {
                activityCategories.add(new String[]{rs.getString("ACTIVITIES_ID"), rs.getString("CATEGORIES_ID")});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("activityCategories", activityCategories);

        List<String[]> packageActivities = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PACKAGE_ACTIVITIES")) {
            while (rs.next()) {
                packageActivities.add(new String[]{rs.getString("PACKAGES_ID"), rs.getString("ACTIVITIES_ID")});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("packageActivities", packageActivities);

        List<String[]> userRoles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USERS_ROLES")) {
            while (rs.next()) {
                userRoles.add(new String[]{rs.getString("USERS_ID"), rs.getString("ROLES_ID")});
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("userRoles", userRoles);
    }
}
