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
public class UserForm {

    private String name;

    private String password;

    private Role role;
}