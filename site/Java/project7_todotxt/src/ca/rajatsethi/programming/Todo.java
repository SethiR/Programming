/*
Author : Rajat Sethi
Date   : 2019-02-01
Desc   : Class for a single todo_item.
 */

package ca.rajatsethi.programming;

import java.time.LocalDate;

public class Todo {
    private final String SEPERATOR = " | ";
    private boolean isComplete;
    private LocalDate dueDate;
    private String project;
    private String description;

    /*      Constructors      */
    public Todo(String description, String project, LocalDate dueDate) {
        this.isComplete = false;
        this.dueDate = dueDate;
        this.project = project;
        this.description = description;
    }

    public Todo(String description) {
        this.description = description;
    }

    public Todo(String description, LocalDate dueDate) {
        this.dueDate = dueDate;
        this.description = description;
    }

    /*      Convert the object to String */
    @Override
    public String toString() {
        return Boolean.toString(isComplete) + SEPERATOR + description + SEPERATOR + project + SEPERATOR + dueDate;
    }

    public void markComplete(){
        isComplete = true;
    }
}
