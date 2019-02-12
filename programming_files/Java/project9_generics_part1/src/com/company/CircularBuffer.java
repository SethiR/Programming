package com.company;

public class CircularBuffer<T>{
    private T[] buffer;
    private int readCursor;
    private int writeCursor;

    public CircularBuffer(int size){
        buffer = (T[]) new Object[size];  // creating an object array of size and then cast to type T
    }

    public boolean offer(T value)
    {
        if (buffer[writeCursor] != null)
        {
            return false;
        }

        buffer[writeCursor] = value;
        writeCursor = next(writeCursor);

        return true;
    }

    public T poll()
    {
        final T value = buffer[readCursor];

        if (value != null)
        {
            buffer[readCursor] = null;
            readCursor = next(readCursor);
        }
        return value;
    }

    private int next(int index)
    {
        return (index + 1) % buffer.length;
    }

    public static void main(String[] args)
    {
        CircularBuffer<String> buffer = new CircularBuffer<>(10);

        buffer.offer("a");
        buffer.offer("bc");
        buffer.offer("d");

        StringBuilder result = new StringBuilder();

        String value;

        while ((value = buffer.poll()) != null)
        {
            result.append(value);
        }

        System.out.println(result.toString());

    }

}