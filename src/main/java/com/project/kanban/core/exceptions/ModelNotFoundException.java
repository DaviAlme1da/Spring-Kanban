package com.project.kanban.core.exceptions;

public class ModelNotFoundException extends RuntimeException{
    
    public ModelNotFoundException(String massage){
        super(massage);
    }
}
