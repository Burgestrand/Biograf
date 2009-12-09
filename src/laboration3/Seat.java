package laboration3;

import javax.swing.JComponent;

/**
 * A model of an individual seat.
 * @author Kim Burgestrand
 */
public class Seat extends JComponent
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
     * Retrieves’ the current status.
     * @return
     */
    public Status status()
    {
        return status;
    }

    /**
     * Sets the new status and returns the old.
     * @param newstatus
     * @return
     */
    public Status status(Status newstatus)
    {
        Status old = status;
        status = newstatus;
        return old;
    }
}
