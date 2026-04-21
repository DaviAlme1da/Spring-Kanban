package com.project.kanban.web.users.mappers;

import org.springframework.stereotype.Component;

import com.project.kanban.core.models.User;
import com.project.kanban.web.users.dtos.UserForm;
import com.project.kanban.web.users.dtos.UserListem;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {


    @Override
    public UserListem toUserListem(User user) {
        return UserListem.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    @Override
    public UserForm toUserForm(User user) {
        return UserForm.builder()
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    @Override
    public User toUser(UserForm userForm) {
        return User.builder()
                .name(userForm.getName())
                .password(userForm.getPassword()) 
                .role(userForm.getRole())
                .build();
    }
}