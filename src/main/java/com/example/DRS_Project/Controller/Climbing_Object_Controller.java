package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Repository.Climbing_Object_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.DRS_Project.Model.Climbing_Object;

@RestController
@RequestMapping("/ClimbingObjects")
public class Climbing_Object_Controller {
    @Autowired
    private Climbing_Object_Repo ClimbingObjRepo;

    @PostMapping("/add")
    public void addWall(@RequestBody Climbing_Object ClimbingObj) {
        ClimbingObjRepo.save(ClimbingObj);
    }
    @GetMapping("/{id}")
    public Climbing_Object getClimbingObjectById(@PathVariable String id) {
        return ClimbingObjRepo.findById(id).orElse(null);
    }
    @GetMapping("/type/{type}")
    public List<Climbing_Object> getClimbingObjects(@PathVariable String type) {
        return ClimbingObjRepo.findByType(type);
    }

}
