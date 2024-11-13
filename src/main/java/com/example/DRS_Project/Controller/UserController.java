package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Services.UserRegistrationService;

@RestController
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println("Enter");
        return userRegistrationService.registerUser(user);
    }

}
