package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Repository.ClimbingObject_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.DRS_Project.Model.ClimbingObject;

@RestController
@RequestMapping("/ClimbingObjects")
public class ClimbingObject_Controller {
    @Autowired
    private ClimbingObject_Repo ClimbingObjRepo;

    @PostMapping("/add")
    public void addWall(@RequestBody ClimbingObject ClimbingObj) {
        ClimbingObjRepo.save(ClimbingObj);
    }
    @GetMapping("/{id}")
    public ClimbingObject getClimbingObjectById(@PathVariable String id) {
        return ClimbingObjRepo.findById(id).orElse(null);
    }
    @GetMapping("/type/{type}")
    public List<ClimbingObject> getClimbingObjects(@PathVariable String type) {
        return ClimbingObjRepo.findByType(type);
    }

}
