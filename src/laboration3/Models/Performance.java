package laboration3.Models;


/**
 * A model of an entire performance (seats, time etc.)
 *
 * @author Kim Burgestrand
 */
public class Performance
{
    // All seats
    private Seat[][] seats;
    private String time;

    /**
     * Creates a new Performance object with the rows*cols amount of seats
     * available for booking at the given time.
     *
     * @param rows  Must be > 0
     * @param cols  Must be > 0
     * @param time  The time this performance is performed
     */
    public Performance(int rows, int cols, String time)
    {
        if (rows <= 0 || cols <= 0)
        {
            throw new RuntimeException("Neither rows nor columns can be less than, or equal to, zero.");
        }

        seats = new Seat[rows][cols];
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                seats[row][col] = new Seat(row, col);
            }
        }

        this.time = time;
    }

    /**
     * @return  The array of seats this performance has (no matter the status)
     */
    public Seat[][] seats()
    {
        return seats;
    }

    /**
     * @param row   Must be >= 1
     * @return      The list of seats at the given row
     */
    public Seat[] seats(int row)
    {
        return seats[row - 1];
    }

    /**
     * @param row >= 1
     * @param col >= 1
     * @return  The seat at the given row and column.
     * @throws IndexOutOfBoundsException
     */
    public Seat seats(int row, int col)
    {
        return seats[row - 1][col - 1];
    }

    @Override
    public String toString()
    {
        return time;
    }
}
