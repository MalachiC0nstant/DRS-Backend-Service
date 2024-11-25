package com.example.DRS_Project.Services;

import com.example.DRS_Project.Model.ProjectCard;
import com.example.DRS_Project.Model.User;
import com.example.DRS_Project.Repository.ProjectCard_Repo;
import com.example.DRS_Project.Repository.User_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectCard_Service {

    @Autowired
    private ProjectCard_Repo projectCardRepository;

    @Autowired
    private User_Repo userRepository;

    public List<ProjectCard> getProjectCardsForUser(ObjectId userId) {
        return userRepository.findById(userId)
                .map(User::getProjects)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public ProjectCard createProjectCard(ObjectId userId, ProjectCard projectCard) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        projectCard.setUserId(userId);
        projectCard.setDateCreated(new Date());
        projectCard.setDateModified(new Date());
        ProjectCard savedProjectCard = projectCardRepository.save(projectCard);

        user.getProjects().add(savedProjectCard);
        userRepository.save(user);

        return savedProjectCard;
    }

    public boolean deleteProjectCardById(ObjectId projectId, ObjectId userId) {
        Optional<ProjectCard> projectCard = projectCardRepository.findById(projectId);
        if (projectCard.isPresent() && projectCard.get().getUserId().equals(userId)) {
            projectCardRepository.deleteById(projectId);
            return true;
        }
        return false;
    }

    public Optional<ProjectCard> duplicateProjectCardById(ObjectId projectId, ObjectId userId) {
        Optional<ProjectCard> originalProject = projectCardRepository.findById(projectId);

        if (originalProject.isPresent() && originalProject.get().getUserId().equals(userId)) {
            ProjectCard newProject = new ProjectCard();

            newProject.setUserId(userId);
            newProject.setName(originalProject.get().getName() + " (Copy)");
            newProject.setSnapshotUrl(originalProject.get().getSnapshotUrl());
            newProject.setDateCreated(new Date());
            newProject.setDateModified(new Date());
            newProject.setNumberOfHolds(originalProject.get().getNumberOfHolds());

            ProjectCard savedProjectCard = projectCardRepository.save(newProject);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.getProjects().add(savedProjectCard);
            userRepository.save(user);
            return Optional.of(savedProjectCard);
        }

        return Optional.empty();
    }

}

