package com.project.kanban.web.users.dtos;

import com.project.kanban.core.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListem {

    private Long id;

    private String name;

    private Role role;
}