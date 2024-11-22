package com.example.DRS_Project.Repository;

import com.example.DRS_Project.Model.ClimbingObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ClimbingObject_Repo extends MongoRepository<ClimbingObject, String> {
    List<ClimbingObject> findByType(String type);
}
