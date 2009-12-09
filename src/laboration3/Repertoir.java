package laboration3;

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
        movies.add(new Movie("Happy Gilmore", new Performance(12, 10)));
        movies.add(new Movie("Interstate 60", new Performance(10, 10)));
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
