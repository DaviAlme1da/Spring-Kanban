package com.project.kanban.core.services.users;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.kanban.core.exceptions.UserNotFoundException;
import com.project.kanban.core.models.User;
import com.project.kanban.core.repositories.UserRepository;
import com.project.kanban.web.users.dtos.UserForm;
import com.project.kanban.web.users.dtos.UserListem;
import com.project.kanban.web.users.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserListem> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserListem)
                .toList();
    }

    public User save(UserForm userForm) {
        var user = userMapper.toUser(userForm);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return userRepository.save(user);
    }

    public UserForm update(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserForm)
                .orElseThrow(UserNotFoundException::new);
    }

    public User update(Long id, UserForm userForm) {
        var userUpdate = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userUpdate.setName(userForm.getName());
        userUpdate.setRole(userForm.getRole());

        if (userForm.getPassword() != null && !userForm.getPassword().isBlank()) {
            userUpdate.setPassword(passwordEncoder.encode(userForm.getPassword()));
        }

        return userRepository.save(userUpdate);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}