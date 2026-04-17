package com.project.kanban.web.Tasks.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.kanban.core.services.tasks.TasksService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/projects/{idProject}/tasks")
public class TasksController {
    
    private final TasksService tasksService;

    @GetMapping
    public ModelAndView index(@PathVariable Long idProject){

        var model = Map.of("TasksListItem", tasksService.index(idProject));

        return new ModelAndView("tasks/index", model);

    }

    @GetMapping("/details/{id}")
    public ModelAndView getMethodName(@PathVariable Long id) {
        
        var model = Map.of("TaskDetails", tasksService.details(id));

        return new ModelAndView("tasks/details",model);
    }
    
}
