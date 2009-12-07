package laboration3;

import java.awt.Rectangle;

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
     * MVC-violating code
     */
    public Rectangle rectangle;

    /**
     * Create a new available seat.
     */
    public Seat ()
    {
        this.status = Status.Available;
    }
}
