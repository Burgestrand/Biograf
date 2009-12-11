package laboration3.Models;

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
        ArrayList<Performance> perfs;

        perfs = new ArrayList<Performance>();
        perfs.add(new Performance(new Salon(5, 5), "16:00"));
        perfs.add(new Performance(new Salon(12, 10), "21:30"));
        movies.add(new Movie("Happy Gilmore", perfs));

        perfs = new ArrayList<Performance>();
        perfs.add(new Performance(new Salon(8, 5), "07:00"));
        perfs.add(new Performance(new Salon(12, 10), "18:00"));
            movies.add(new Movie("Bollibompa", perfs));

        return new Repertoir(movies);
    }
}
