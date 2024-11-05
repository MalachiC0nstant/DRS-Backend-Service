package com.example.DRS_Project.Repository;

import com.example.DRS_Project.Model.Climbing_Object;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface Climbing_Object_Repo extends MongoRepository<Climbing_Object, String> {
    List<Climbing_Object> findByType(String type);
}
