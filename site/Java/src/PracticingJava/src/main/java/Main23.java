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
