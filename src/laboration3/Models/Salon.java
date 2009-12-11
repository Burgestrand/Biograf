package laboration3.Models;

/**
 * A model of a salon: contains all the seats.
 * 
 * @author Kim Burgestrand
 * @since v2.0.1
 */
public class Salon
{
	private Seat[][] seats;
	
	/**
	 * Creates a new salon of the default size: 10x10.
	 */
	public Salon()
	{
		this(10, 10);
	}
	
	/**
	 * Creates a new salon of the given size.
	 * @param rows		the number of rows
	 * @param columns	the number of columns
	 */
	public Salon(int rows, int columns)
	{
        seats = new Seat[rows][columns];
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < columns; ++col)
            {
                seats[row][col] = new Seat(row, col);
            }
        }
	}
	
	/**
	 * Returns all the seats within this salon.
	 */
	public Seat[][] seats()
	{
		return seats;
	}
}
