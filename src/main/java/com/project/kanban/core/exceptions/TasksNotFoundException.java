package com.project.kanban.core.exceptions;

public class TasksNotFoundException extends ModelNotFoundException {
    
    public TasksNotFoundException(String massage) {
        super(massage);
    }

    public TasksNotFoundException() {
        super("Tarefa não encontrado");
    }

}
