package com.example.DRS_Project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class User {
    @Id
    private ObjectId userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @DBRef
    private List<ProjectCard> projects = new ArrayList<>();
}
