package com.project.kanban.web.common.handlers;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.project.kanban.core.exceptions.ModelNotFoundException;

@ControllerAdvice
public class WebExceptionHandler {
    
    @ExceptionHandler(ModelNotFoundException.class)
    public ModelAndView handleModelNotFoundException(ModelNotFoundException e) {
        var model = Map.of(
                "message", e.getMessage(),
                "status", 404);
        return new ModelAndView("error/error", model);
    }
}
