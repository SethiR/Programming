/*
Comparable Interface.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Employee implements Comparable<Employee>{

    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Employee o) {
        return id - o.id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class Main13{
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(109, "sam nelson"));
        employees.add(new Employee(10, "pete nelson"));
        employees.add(new Employee(11, "becky nelson"));

        System.out.println(employees.get(0).compareTo(employees.get(1)));

        Collections.sort(employees);

        for (Employee employee : employees){
            System.out.println(employee);
        }

    }
}