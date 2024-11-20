package com.example.DRS_Project.Repository;

import com.example.DRS_Project.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface User_Repo extends MongoRepository<User, Integer> {

    boolean existsByEmail(String email);
    User findByEmail(String email);
}
