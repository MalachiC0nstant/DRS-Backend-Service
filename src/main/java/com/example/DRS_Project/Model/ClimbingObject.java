package com.example.DRS_Project.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClimbingObject {
    @Id
    private Integer object_id;
    private String object_name;
    private String image_url; // Unsure about this for now
    private Map<String, Object> threejs_data;
    private String type; // crimp, jug, sloper, volume, wall
}
