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
    private Status status;
    private int row, col;

    /**
     * Create a new available seat.
     * @param row
     * @param col 
     */
    public Seat (int row, int col)
    {
        this.col = col;
        this.row = row;
        status = Status.Available;
    }

    /**
     * @return  The seats’ current status
     */
    public Status status()
    {
        return status;
    }

    /**
     * @return  The seats’ row ([0..])
     */
    public int row()
    {
        return row;
    }

    /**
     * @return  The seats’ column ([0..])
     */
    public int col()
    {
        return col;
    }

    /**
     * Sets the new status and returns the old.
     * @param newstatus
     * @return  the status that was set before change
     */
    public Status status(Status newstatus)
    {
        Status old = status;
        status = newstatus;
        return old;
    }
}
