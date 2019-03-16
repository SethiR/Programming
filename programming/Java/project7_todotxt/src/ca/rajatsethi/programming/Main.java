package ca.rajatsethi.programming;

import java.time.LocalDate;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        TodoList todoList = new TodoList();

        Todo todo1 = new Todo("Buy Milk");
        todoList.addTask(todo1);

        Todo todo2 = new Todo("Buy Eggs");
        todoList.addTask(todo2);

        Todo todo3 = new Todo("Return File", LocalDate.of(2019,2,15));
        todoList.addTask(todo3);

        todo3.markComplete();


        for (Todo todo : todoList){
            System.out.println(todo);
        }

    }
}
