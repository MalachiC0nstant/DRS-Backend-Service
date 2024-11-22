package com.example.DRS_Project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Model.LoginRequest;
import com.example.DRS_Project.Services.UserCredentials_Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
public class User_Controller {

   // private static final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";
    private final UserCredentials_Service userCredentialsService;

    @Autowired
    public User_Controller(UserCredentials_Service userCredentialsService) {
        this.userCredentialsService = userCredentialsService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userCredentialsService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest, HttpSession session) {
        boolean isAuthenticated = userCredentialsService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            session.setAttribute("email", loginRequest.getEmail());
            return ResponseEntity.ok("Login successful. Session ID: " + session.getId()); // Maybe bad habit?
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed. Invalid email or password."); // Maybe bad habit?
        }
    }

    @GetMapping("/isAuthenticated")
    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("email") != null;
    }

    @PostMapping("/logoutUser")
    public String logoutUser(HttpSession session) {
        session.invalidate(); //Useless for now
        return "Logout successful.";
    }

//    @GetMapping("/test")
//    public String home(Principal principal, HttpSession session){
//        incrementCount(session, HOME_VIEW_COUNT);
//        return "Hello, " + principal.getName();
//    }
//
//    @GetMapping("/count")
//    public String count(HttpSession session) {
//        return "HOME_VIEW_COUNT: " + session.getAttribute(HOME_VIEW_COUNT);
//    }
//    private void incrementCount(HttpSession session, String attr) {
//        var homeViewCount = session.getAttribute(attr) == null ? 0 : (Integer) session.getAttribute(attr);
//        session.setAttribute(attr, homeViewCount+=1);
//    }
}
