package com.example.DRS_Project.Repository;

import com.example.DRS_Project.Model.ProjectCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectCard_Repo extends MongoRepository<ProjectCard, String> {

}
