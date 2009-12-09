package laboration3;

/**
 * A model of a specific Movie
 * @author Kim Burgestrand
 */
public class Movie
{
    private String name;
    private Performance performance;

    /**
     * @param name  Movie name
     * @param perf  Movie performance object
     */
    public Movie (String name, Performance perf)
    {
        this.name        = name;
        this.performance = perf;
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
    public Performance performance()
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
