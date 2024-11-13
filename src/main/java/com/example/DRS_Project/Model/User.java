package com.example.DRS_Project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
