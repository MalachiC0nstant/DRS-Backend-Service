package com.example.DRS_Project.Controller;

import com.example.DRS_Project.Model.ProjectCard;
import com.example.DRS_Project.Services.ProjectCard_Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/projectcard")
public class ProjectCard_Controller {

    @Autowired
    private ProjectCard_Service projectCardService;

    @GetMapping("/projects/{projectId}")
    public List<ProjectCard> getProjectCard(@PathVariable String userId, HttpSession session) {

        ObjectId objectId = new ObjectId("673e723d41d66b545257ee4d");
        return projectCardService.getProjectCardsForUser(objectId);
    }

    @GetMapping("/projects")
    public List<ProjectCard> getProjectCardsForUser(HttpSession session) {
        ObjectId userId = (ObjectId) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        return projectCardService.getProjectCardsForUser(userId);
    }

    @PostMapping("/create")
    public ProjectCard createProjectCard(HttpSession session, @RequestBody ProjectCard projectCard) {
        ObjectId userId = (ObjectId) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        return projectCardService.createProjectCard(userId, projectCard);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProjectCard(@PathVariable String id, HttpSession session) {
        ObjectId userId = (ObjectId) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }

        ObjectId  projectId = new ObjectId(id);
        boolean isDeleted = projectCardService.deleteProjectCardById(projectId, userId);
        if (isDeleted) {
            return ResponseEntity.ok("Project deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found or access denied.");
        }
    }

    @PostMapping("/duplicate/{id}")
    public ResponseEntity<ProjectCard> duplicateProjectCard(@PathVariable String id, HttpSession session) {
        ObjectId userId = (ObjectId) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        ObjectId  projectId = new ObjectId(id);
        Optional<ProjectCard> duplicatedProject = projectCardService.duplicateProjectCardById(projectId, userId);
        if (duplicatedProject.isPresent()) {
            return ResponseEntity.ok(duplicatedProject.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}