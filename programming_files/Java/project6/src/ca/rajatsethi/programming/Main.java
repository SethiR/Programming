package ca.rajatsethi.programming;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        // Instanciating a new queue of type linked list
        Queue<Integer> q = new LinkedList<>();

        // adding elements to queue
        q.add(10);
        q.add(20);

        // printing out the queue
        System.out.println(q);

        // get first element
        System.out.println(q.element());
        System.out.println(q);

        // iterating over queue
        for (int i: q ) { System.out.println(i); }

        // remove element from the queue
        System.out.println("Remove element from queue : " + q.remove());
        System.out.println(q);
    }
}
