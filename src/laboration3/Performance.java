package laboration3;

import java.util.ArrayList;

/**
 * A model of all seats and their status.
 *
 * @author Kim Burgestrand
 */
public class Performance
{
    // All seats
    private Seat[][] seats;

    /**
     * Builds a performance with all available seats.
     *
     * @param rows  Must be > 0
     * @param cols  Must be > 0
     */
    public Performance(int rows, int cols)
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
                seats[row][col] = new Seat();
            }
        }
    }

    /**
     * @return
     */
    public Seat[][] seats()
    {
        return seats;
    }

    /**
     * @param row   >= 1
     * @return
     */
    public Seat[] seats(int row)
    {
        return seats[row - 1];
    }

    /**
     * @param row >= 1
     * @param col >= 1
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Seat seats(int row, int col)
    {
        return seats[row - 1][col - 1];
    }

    @Override
    public String toString()
    {
        String ret = "";
        for (Seat[] row : seats)
        {
            for (Seat s : row)
            {
                ret += "[" + s.status.toString().substring(0, 1) + "]";
            }

            ret.trim();
            ret += "\n";
        }

        return ret.trim();
    }
}
