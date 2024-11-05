package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.DRS_Project.Model.User;

@RestController
public class MainController {
    @Autowired
    User_Repo user_repo;

    @PostMapping("/addUser")
    public void add_User(@RequestBody User user){
        user_repo.save(user);

    }
}
