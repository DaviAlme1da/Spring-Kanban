package com.project.kanban.web.Tasks.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.kanban.core.services.tasks.TasksService;
import com.project.kanban.web.Tasks.dtos.TaskForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects/{idProject}/tasks")
public class TasksController {

    private final TasksService tasksService;

    @GetMapping
    public ModelAndView index(@PathVariable Long idProject) {

        var model = Map.of(
                "TasksListItem", tasksService.index(idProject),
                "idProject", idProject,
                "pageTitle", "Quadro Kanban");

        return new ModelAndView("tasks/index", model);

    }

    @GetMapping("/create")
    public ModelAndView create(@PathVariable Long idProject) {
        var model = Map.of(
                "TaksForm", new TaskForm(),
                "pageTitle", "Criar Tarefa",
                "pageSubtitle", "Novo item",
                "submitLabel", "Adicionar Task",
                "formAction", "/projects/" + idProject + "/tasks/create",
                "idProject", idProject);
        return new ModelAndView("tasks/form", model);
    }

    @PostMapping("/create")
    public String create(@PathVariable Long idProject, TaskForm taskForm) {

        tasksService.save(taskForm, idProject);

        return "redirect:/projects/" + idProject + "/tasks";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long idProject, @PathVariable Long id) {
        var model = Map.of(
                "TaksForm", tasksService.update(id),
                "pageTitle", "Editar Tarefa",
                "pageSubtitle", "Atualizar dados",
                "submitLabel", "Salvar Alterações",
                "formAction", "/projects/" + idProject + "/tasks/edit/" + id,
                "idProject", idProject);
        return new ModelAndView("tasks/form", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long idProject,
            @PathVariable Long id,
            TaskForm taskForm) {

        tasksService.update(id, taskForm);

        return "redirect:/projects/" + idProject + "/tasks";
    }

    @GetMapping("/moveToNextStatus/{id}")
    public String moveToNextStatus(@PathVariable Long idProject, @PathVariable Long id) {
        tasksService.moveToNextStatus(id);
        return "redirect:/projects/" + idProject + "/tasks";
    }

    @GetMapping("/moveToPreviousStatus/{id}")
    public String moveToPreviousStatus(@PathVariable Long idProject, @PathVariable Long id) {
        tasksService.moveToPreviousStatus(id);
        return "redirect:/projects/" + idProject + "/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long idProject, @PathVariable Long id) {
        tasksService.delete(id);
        return "redirect:/projects/" + idProject + "/tasks";
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable Long id) {

        var model = Map.of(
                "TaskDetails", tasksService.details(id),
                "pageTitle", "Detalhes da Tarefas");

        return new ModelAndView("tasks/details", model);
    }

}
