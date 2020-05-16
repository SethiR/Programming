package ca.rajatsethi.programming;

import java.util.ArrayList;
import java.util.Iterator;

public class TodoList implements Iterable<Todo>{

    ArrayList<Todo> todoList = new ArrayList<>();

    public TodoList() {

    }

    public void addTask(Todo todo) {
        todoList.add(todo);
    }

    @Override
    public Iterator<Todo> iterator(){
        return todoList.iterator();
    }
}
