package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List list = new ArrayList();

        list.add(1);
        list.add("Hello world");

        for (Object a: list) {
            System.out.println(a);
        }

    }

    List<String> x = new ArrayList<>();
}
