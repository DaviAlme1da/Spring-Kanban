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

        var model = Map.of("TasksListItem", tasksService.index(idProject));

        return new ModelAndView("tasks/index", model);

    }

    @GetMapping("/create")
    public ModelAndView create() {
        var model = Map.of("TaksForm", new TaskForm());

        return new ModelAndView("tasks/create", model);
    }

    @PostMapping("/create")
    public String create(@PathVariable Long idProject, TaskForm taskForm) {

        tasksService.save(taskForm, idProject);

        return "redirect:/projects/{idProject}/tasks";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {

        var model = Map.of("TaksForm", tasksService.update(id));

        return new ModelAndView("tasks/create", model);
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, TaskForm taskForm) {

        tasksService.update(id, taskForm);

        return "redirect:/projects/{idProject}/tasks";
    }

    @GetMapping("/moveToNextStatus/{id}")
    public String moveToNextStatus(@PathVariable Long id) {

        tasksService.moveToNextStatus(id);

        return "redirect:/projects/{idProject}/tasks";
    }

    @GetMapping("/moveToPreviousStatus/{id}")
    public String moveToPreviousStatus(@PathVariable Long id) {

        tasksService.moveToPreviousStatus(id);

        return "redirect:/projects/{idProject}/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        tasksService.delete(id);

        return "redirect:/projects/{idProject}/tasks";
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable Long id) {

        var model = Map.of("TaskDetails", tasksService.details(id));

        return new ModelAndView("tasks/details", model);
    }

}
