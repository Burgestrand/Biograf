package laboration3;

import java.util.ArrayList;

/**
 * A model of a specific Movie
 * @author Kim Burgestrand
 */
public class Movie
{
    private String name;
    private ArrayList<Performance> performance = new ArrayList();

    /**
     * @param name  Movie name
     * @param perf  Movie performance object
     */
    public Movie (String name)
    {
        this.name = name;
    }

    /**
     * @return
     */
    public String name()
    {
        return name;
    }

    /**
     * @return
     */
    public ArrayList<Performance> performance()
    {
        return performance;
    }

    /**
     * @return
     */
    public String toString()
    {
        return name;
    }
}
