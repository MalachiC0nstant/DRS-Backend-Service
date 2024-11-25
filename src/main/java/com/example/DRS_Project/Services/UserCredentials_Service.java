package com.example.DRS_Project.Services;

import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Repository.User_Repo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentials_Service {

    @Autowired
    private User_Repo userRepository;

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already in use.";
        }

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return "Registration successful.";
    }

    public Optional<String> loginUser(String email, String password, HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            session.setAttribute("email", email);
            session.setAttribute("userId", user.getUserId());
            return Optional.of("Login successful");
        } else {
            return Optional.empty();
        }
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
