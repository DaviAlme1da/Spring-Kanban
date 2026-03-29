package com.project.kanban.web.projects.dtos;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsForm {
    
    private String name;

    private String description; 

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate startDate;

    private Long owner;

}
