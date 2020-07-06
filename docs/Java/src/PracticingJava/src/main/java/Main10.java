/*
Java Iterable and Iterator Example.
 */

import java.util.Iterator;

class GenericList implements Iterable<Integer>{
    int[] mylist = new int[10];
    int position = 0;

    void push(int value){
        mylist[position++] = value;
    }

    int pop(){
        return mylist[--position];
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator(this);
    }

    public class CustomIterator implements Iterator<Integer>{

        GenericList list;
        private int pointer = 0;

        CustomIterator(GenericList list){
            this.list = list;
        }
        @Override
        public boolean hasNext() {
            return pointer < list.position;
        }

        @Override
        public Integer next() {
            return list.mylist[pointer++];
        }
    }
}

public class Main10 {
    public static void main(String[] args) {
        GenericList lst = new GenericList();
        lst.push(10);
        lst.push(20);

//        System.out.println(lst.pop());

        for (int value :lst){
            System.out.println(value);
        }
    }
}