package com.project.kanban.core.exceptions;

public class ProjectsNotFoundException extends ModelNotFoundException {

    public ProjectsNotFoundException(String massage) {
        super(massage);
    }

    public ProjectsNotFoundException() {
        super("Projeto não encontrado");
    }
    
}
