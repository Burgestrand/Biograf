package laboration3;

/**
 * A model of an individual seat.
 * @author Kim Burgestrand
 */
public class Seat
{
    public enum Status
    {
        Available,
        Booked,
        Sold
    }

    /**
     * The Seats’ status: Available, Booked or Sold
     */
    public Status status;

    /**
     * Create a new available seat.
     */
    public Seat ()
    {
        this.status = Status.Available;
    }
}
