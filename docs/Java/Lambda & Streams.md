# Lambda and Streams

## Lambda

Lets start with a simple example. To print stuff on the console we created a ConsolePrinter class. The contract is simple and implemented by the interface Printer. 


```java
// A functional interface which has 1 unimplemented method as below. (It can have other default methods which are implemented)
interface Printer{
    void print(String message);
}

class ConsolePrinter implements Printer{
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

public class Main14 {
    public static void main(String[] args) {
        show();
    }

    public static void show(){
        greet(new ConsolePrinter());
    }
    public static void greet(Printer printer){
        printer.print("Hello World");
    }
}
```

But why create a class (where actually it will be a .java file), instead lets create anonymus class to replace the ConsolePrinter class.

```java
// A functional interface which has 1 unimplemented method as below. (It can have other default methods which are implemented)
interface Printer{
    void print(String message);
}

public class Main14 {
    public static void main(String[] args) {
        show();
    }

    public static void show(){
        greet(new Printer(){  // Anonymous class
            @Override
            public void print(String message) {
                System.out.println(message);
            }
        });
    }
    public static void greet(Printer printer){
        printer.print("Hello World");
    }
}
```

Java 8 provided a simpler way to achieve the same result, without writing anonymous inner class. Its called a lambda expression.


```java
// A functional interface which has 1 unimplemented method as below. (It can have other default methods which are implemented)
interface Printer{
    void print(String message);
}

public class Main14 {
    public static void main(String[] args) {
        show();
    }

    public static void show(){
        greet( (message) -> System.out.println(message));  // lambda expression
    }
    public static void greet(Printer printer){
        printer.print("Hello World");
    }
}
```

You can also access local parameters in lambda expressions.
```java
public static void show(){
    String prefix = "-";
    greet( message -> System.out.println(prefix + message));  // lambda expression
}
```

__Method References__

Sometimes all we do in lambda expression is to pass the variable along to another function such as above where we pass it to println. In this case we can use method references as shown below.

```java
id show(){
    String prefix = "-";
    greet( message -> System.out::println));  // Class::method  <-- method reference, static or instance or even a constructor.
}
```

__Types of Functional Interfaces in Java__

[Oracle Docs Link](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/package-summary.html)

`java.util.function.` has all the functional interfaces in Java.

- Consumer : Takes single argument and returns no result.
- Supplier : Takes no input and returns output.
- Function : Map a value to a different value
- Predicate: Takes an input and checks whether that input satifies a criteria. Boolean return.


Consumer Interface

```java
/*
Consumer Interface.
 */

import java.util.ArrayList;
import java.util.List;

public class Main15 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(System.out::println); // forEach looks for consumer interface and we created a lambda implementation for that consumer interface.
    }
}

```

Chaining consumers.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main16 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // Creating an implementation of Consumer Interface.
        Consumer<String> print = System.out::println;
        Consumer<String> printUpperCase = (item) -> System.out.println(item.toUpperCase());

        // Chaining consumers.
        list.forEach(print.andThen(printUpperCase).andThen(print));
    }
}
```

The supplier interface.

```java
import java.util.function.Supplier;

public class Main17 {
    public static void main(String[] args) {

        // Check javadoc for Supplier Interface.
        Supplier<Double> getRandom = Math::random;

        System.out.println(getRandom.get());

    }
}
```

Example of Function interface. Again as usual check out the java docs to see the contract of the function interface.

```java
import java.util.function.Function;

public class Main18 {
    public static void main(String[] args) {
        Function<String, Integer> map = String::length;
        System.out.println(map.apply("hello world"));
    }
}
```

```java
/*
Another more complicated example of Function
 */

import java.util.function.Function;

public class Main19 {
    public static void main(String[] args) {

        Function<String, String> replace = (str) -> str.replace(":","=");
        Function<String, String> addBraces = (str) -> "{" + str + "}";

        String result = replace
                            .andThen(addBraces)
                            .apply("key:value");

        System.out.println(result);

    }
}
```

The predicate interface.

```java
import java.util.function.Predicate;

public class Main20 {
    public static void main(String[] args) {

        Predicate<String> isLongerThan5 = (str) -> str.length() > 5;
        Boolean result = isLongerThan5.test("Hello World");
        System.out.println(result);
    }
}
```

Combining predicate.

```java
import java.util.function.Predicate;

public class Main21 {
    public static void main(String[] args) {

        Predicate<String> hasLeftBrace = (str) -> str.startsWith("{");
        Predicate<String> hasRightBrace = (str) -> str.endsWith("}");

        System.out.println(hasLeftBrace.and(hasRightBrace).test("{hello world}"));
        System.out.println(hasLeftBrace.and(hasRightBrace).test("{hello worldf"));

    }
}
```


## Streams

Streams were added to Java so that we can process a logic in functional way. Every collection in Java returns a stream of data.


```java
/*
Streams
 */

import java.util.ArrayList;
import java.util.List;

class Movie{
    private final String name;
    private final int likes;

    public Movie(String name, int likes) {
        this.name = name;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }
}

public class Main22 {
    public static void main(String[] args) {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("a", 10));
        movies.add(new Movie("b", 15));
        movies.add(new Movie("c", 20));

        long count = movies.stream()
                    .filter((movie) -> movie.getLikes() > 10)
                    .count();

        System.out.println(count);

        
    }
}

```

We can create a stream from 
- Collections e.g. `list.stream()`
- Array e.g. `Arrays.stream({1,2,3})`
- Arbitrary # of objects e.g. `Stream.of(new A2(), new A2()).forEach(System.out::println);`
- Infinite/Finite Streams. e.g. `Stream.generate(() -> Math.random())` You can also limit it by using `.limit(20)` to not have infinite stream.
Another way --> `Stream.iterate(1, n -> n+1).limit(20).forEach(System.out::println);`

__Map method__

Applys that method to all objects/values in the stream.

```java
/*
Streams
 */

import java.util.ArrayList;
import java.util.List;

class Movie1{
    private final String name;
    private final int likes;

    public Movie1(String name, int likes) {
        this.name = name;
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }
}

public class Main23 {
    public static void main(String[] args) {

        List<Movie1> movies = new ArrayList<>();
        movies.add(new Movie1("a", 10));
        movies.add(new Movie1("b", 15));
        movies.add(new Movie1("c", 20));

        movies.stream()
                .map(movie -> movie.getName())
                .forEach(System.out::println);

    }
}
```

__Flat Map__

 Lets say if we have a steam of list of integers e.g. integers listed under 2nd hierarchy and we want to work with the intergers use flatmap to get list of integers.

 ```java
 import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main24 {
    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(4);
        list2.add(5);
        list2.add(6);
        
        Stream<List<Integer>> stream = Stream.of(list1, list2);
        
        // Using flatmap
        stream
            .flatMap(list -> list.stream())
            .forEach(System.out::println);

    }
}
```

Stream methods fall into 2 categories
- Intermediate operations -> they return new stream e.g. Filter, Map 
- Terminal operations -> they return void and consume the stream e.g. forEach.

If you do not call terminal operator on your stream nothing happens. This is called lazy evaluation.

__Slicing__

- limit(n)
- skip(n)
- takeWhile(Predicate)
- dropWhile(Predicate)


We should get the first 2 movies from the list.
```java
movies.stream()
    .limit(2)
```


We should skip the first 2 movies from the list.
```java
movies.stream()
    .skip(2)
```

- Takewhile takes a Predicate and returns movies which get true for that predicate. (akeWhile stops the stream on first false from predicate where as filter does not it iterates through entire stream)
- Takewhile takes a Predicate and returns movies which get true for that predicate.