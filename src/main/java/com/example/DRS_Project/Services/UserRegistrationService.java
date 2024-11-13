package com.example.DRS_Project.Services;
import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

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

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}