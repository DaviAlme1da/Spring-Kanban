package com.project.kanban.web.projects.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.kanban.core.services.projects.ProjectsService;
import com.project.kanban.web.projects.dtos.ProjectsForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping
    public ModelAndView index() {

        var model = Map.of("projectsListem", projectsService.findAll());
        return new ModelAndView("projects/index", model);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        var model = Map.of(
                "ProjectsForm", new ProjectsForm(),
                "pageTitle", "Criar Projeto",
                "pageSubtitle", "Novo projeto",
                "submitLabel", "Salvar Projeto",
                "formAction", "/projects/create");

        return new ModelAndView("projects/form", model);
    }

    @PostMapping("/create")
    public String create(ProjectsForm projectsForm) {

        projectsService.save(projectsForm);

        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var form = projectsService.update(id);

        var model = Map.of(
                "ProjectsForm", form,
                "pageTitle", "Editar Projeto",
                "pageSubtitle", "Atualizar dados",
                "submitLabel", "Salvar Alterações",
                "formAction", "/projects/edit/" + id,
                "projectId", id);

        return new ModelAndView("projects/form", model);
    }

    @PostMapping("/edit/{id}")
    public String create(@PathVariable Long id, ProjectsForm projectsForm) {

        projectsService.update(id, projectsForm);

        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        projectsService.delete(id);

        return "redirect:/projects";
    }

    @GetMapping("details/{id}")
    public ModelAndView details(@PathVariable Long id) {

        var model = Map.of("ProjectDetails", projectsService.details(id));

        return new ModelAndView("projects/details", model);
    }
}
