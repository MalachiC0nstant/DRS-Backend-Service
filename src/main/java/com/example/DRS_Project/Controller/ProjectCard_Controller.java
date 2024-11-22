package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Model.ProjectCard;
import com.example.DRS_Project.Services.ProjectCard_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/projectcard")
public class ProjectCard_Controller {

    @Autowired
    private ProjectCard_Service projectCardService;

    @GetMapping("/projects")
    public List<ProjectCard> getProjectCardsForUser() { //@PathVariable String userId
        ObjectId objectId = new ObjectId("673e723d41d66b545257ee4d");
        return projectCardService.getProjectCardsForUser(objectId);
    }

    @PostMapping("/create")
    public ProjectCard createProjectCard(@RequestBody ProjectCard projectCard) {
        ObjectId objectId = new ObjectId("673e723d41d66b545257ee4d");
        return projectCardService.createProjectCard(objectId, projectCard);
    }
}