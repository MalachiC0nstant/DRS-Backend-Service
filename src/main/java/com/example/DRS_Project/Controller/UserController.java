package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Model.LoginRequest;
import com.example.DRS_Project.Services.UserCredentialsService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class UserController {

    private static final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";
    private final UserCredentialsService userCredentialsService;

    @Autowired
    public UserController(UserCredentialsService userCredentialsService) {
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
        String sessionIdBefore = session.getId();
        session.invalidate();
        String sessionIdAfter = session.getId();
        System.out.println("Session before: " + sessionIdBefore);
        System.out.println("Session after invalidate: " + sessionIdAfter);  // Should be null or invalid.
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
