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
public class ProjectsListem {
    
    private Long id;

    private String name;

    private LocalDate startDate;

    private String ownerName;
}
