package com.project.kanban.core.exceptions;

public class UserNotFoundException extends ModelNotFoundException{

    public UserNotFoundException(String massage) {
        super(massage);
    }

     public UserNotFoundException() {
        super("Usuario não encontrado");
    }
    
}
