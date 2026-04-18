package com.project.kanban.web.users.mappers;

import com.project.kanban.core.models.User;
import com.project.kanban.web.users.dtos.UserForm;
import com.project.kanban.web.users.dtos.UserListem;

public interface UserMapper {

    UserListem toUserListem(User user);

    UserForm toUserForm(User user);

    User toUser(UserForm userForm);
}