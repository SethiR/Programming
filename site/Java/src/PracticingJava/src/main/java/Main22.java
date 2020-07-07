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
