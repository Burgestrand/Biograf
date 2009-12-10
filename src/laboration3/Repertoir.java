package laboration3;

import java.util.ArrayList;

/**
 * Contains a repertoir of all available movies.
 * @author Kim Burgestrand
 */
public class Repertoir {
    private ArrayList<Movie> movies;

    public Repertoir(ArrayList<Movie> movies)
    {
        this.movies = movies;
    }

    /**
     * Return the whole repertoir.
     * @return
     */
    public ArrayList<Movie> movies()
    {
        return (ArrayList<Movie>) movies.clone();
    }

    /**
     * Creates an example repertoir.
     */
    public static Repertoir Default()
    {
        ArrayList<Movie> movies = new ArrayList();
        Movie movie;

        movie = new Movie("Happy Gilmore");
              movie.performance().add(new Performance(12, 10, "21:30"));
              movie.performance().add(new Performance(10, 10, "22:30"));
              movies.add(movie);

        movie = new Movie("Bollibompa");
              movie.performance().add(new Performance(12, 10, "07:00"));
              movie.performance().add(new Performance(10, 10, "18:00"));
              movies.add(movie);

        return new Repertoir(movies);
    }
}
