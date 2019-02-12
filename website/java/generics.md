
!!!Note
    Probably need to come back to this course after I study some general concepts of generics from youtube or book which are more basic than this one.

## Basics

The java collections can take any type of value as shown below. This will create a problem because we may not be able to control what type of value will go inside the `list` and cannot program reliably.

```Java
package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List list = new ArrayList();

        list.add(1);
        list.add("HEllo world");

        for (Object a: list) {
            System.out.println(a);
        }

    }
}
```

Instead we should have a list which only contains `Strings` thus we can work reliably with it. This is done with the help of generics similar to in C++.

```Java
List<String> list = new ArrayList<>();
```

Creating a simple generic class.

```Java
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
```



## Using Generics with Classes and Interfaces

**Implementing a Generic Type**

