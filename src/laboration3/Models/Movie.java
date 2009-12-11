package laboration3.Models;

import java.util.ArrayList;
import java.util.Collections;
/**
 * A model of a specific Movie
 * @author Kim Burgestrand
 */
public class Movie implements Comparable<Movie>
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
     * Adds a new performance to the list of performances.
     */
    public void add(Performance p)
    {
    	performance.add(p);
    	Collections.sort(performance);
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

	@Override
	public int compareTo(Movie o) {
		return name.compareTo(o.name());
	}
}
