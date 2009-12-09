package laboration3;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Contains a repertoir of all available movies.
 * @author Kim Burgestrand
 */
public class Repertoir {
    private ArrayList<Movie> movies;

    public Repertoir()
    {
        movies = new ArrayList();
        Movie movie = new Movie("Happy Gilmore");

        movie.performance().add(new Performance(12, 10, "21:30"));
        movie.performance().add(new Performance(10, 10, "22:30"));
        movies.add(movie);
    }

    /**
     * Return the whole repertoir.
     * @return
     */
    public ArrayList<Movie> movies()
    {
        return movies;
    }
}
