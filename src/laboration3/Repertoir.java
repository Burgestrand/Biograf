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
     * @return  The whole repertoir (list of available movies)
     */
    public ArrayList<Movie> movies()
    {
        return new ArrayList<Movie>(movies);
    }

    /**
     * @return  An example repertoir consisting of two movies with two performances
     *          each.
     */
    public static Repertoir Default()
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<Performance> performances;

        performances = new ArrayList<Performance>();
        performances.add(new Performance(12, 10, "21:30"));
        performances.add(new Performance(10, 10, "22:30"));
            movies.add(new Movie("Happy Gilmore", performances));

        performances = new ArrayList<Performance>();
        performances.add(new Performance(12, 10, "07:00"));
        performances.add(new Performance(10, 10, "18:00"));
            movies.add(new Movie("Bollibompa", performances));

        return new Repertoir(movies);
    }
}
