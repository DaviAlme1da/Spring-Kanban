package com.project.kanban.web.projects.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsDetails {
    
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;
   
    private String ownerName;

}
