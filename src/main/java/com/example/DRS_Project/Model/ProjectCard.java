package com.example.DRS_Project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("projects")
public class ProjectCard {
    @Id
    private String id;
    private ObjectId userId; // refences its owner
    private String name;
    private String snapshotUrl;
    private Date dateModified;
    private Date dateCreated;

    private int numberOfHolds;


}

