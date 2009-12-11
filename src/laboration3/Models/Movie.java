package laboration3.Models;

import java.util.ArrayList;


/**
 * A model of a specific Movie
 * @author Kim Burgestrand
 */
public class Movie
{
    private String name;
    private ArrayList<Performance> performance;

    /**
     * Creates a new movie with the given name and list of performances.
     *
     * @param name  Movie name
     * @param performance
     */
    public Movie (String name, ArrayList<Performance> performance)
    {
        this.name = name;
        this.performance = performance;
        
        for (Performance p : performance)
        {
        	p.movie = this;
        }
    }

    /**
     * @return  The name of this movie
     */
    public String name()
    {
        return name;
    }

    /**
     * @return  The list of performances this movie has
     */
    public ArrayList<Performance> performance()
    {
        return new ArrayList<Performance>(performance);
    }

    /**
     * @return  @see name
     */
    public String toString()
    {
        return name;
    }
}
