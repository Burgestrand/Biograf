package laboration3.Models;


/**
 * A model of an entire performance (seats, time etc.)
 *
 * @author Kim Burgestrand
 */
public class Performance implements Comparable<Performance>
{
    // All seats
	private Salon salon;
    private String time;
    public Movie movie;

    /**
     * @param salon	the salon this performance is performed in
     * @param time  the time this performance is performed
     */
    public Performance(Salon salon, String time)
    {
    	this.salon = salon;
        this.time = time;
    }

    /**
     * @return the salon selected for this performance
     */
    public Salon salon()
    {
        return salon;
    }
    
    /**
     * The time this performance is performed.
     * @return time
     */
    public String time()
    {
    	return time;
    }
    
    @Override
    public String toString()
    {
        return time;
    }

    /**
     * Compares a performance object to another
     * @param p
     * @return
     */
	public int compareTo(Performance p) {
		return time.compareTo(p.time());
	}
}
