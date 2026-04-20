package com.project.kanban.web.users.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.kanban.core.services.users.UserService;
import com.project.kanban.web.users.dtos.UserForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ModelAndView index() {
        var model = Map.of("usersListem", userService.findAll());
        return new ModelAndView("users/index", model);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        var model = Map.of(
                "UserForm", new UserForm(),
                "pageTitle", "Criar Usuário",
                "pageSubtitle", "Gerenciamento",
                "submitLabel", "Criar Usuário",
                "formAction", "/users/create",
                "showPassword", true);
        return new ModelAndView("users/form", model);
    }

    @PostMapping("/create")
    public String create(UserForm userForm) {
        userService.save(userForm);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var model = Map.of(
                "UserForm", userService.update(id),
                "pageTitle", "Editar Usuário",
                "pageSubtitle", "Gerenciamento",
                "submitLabel", "Salvar Alterações",
                "formAction", "/users/edit/" + id,
                "showPassword", false); 
        return new ModelAndView("users/form", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, UserForm userForm) {
        userService.update(id, userForm);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}