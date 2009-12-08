package laboration3;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Draws the performance in a JPanel.
 *
 * @author Kim Burgestrand
 */
public class PerformanceView extends JPanel {
    /**
	 * To make eclipse happy
	 */
	private static final long serialVersionUID = 2892178762102639403L;
	private Performance performance;

    /**
     * @param p
     */
    public PerformanceView(Performance p)
    {
        performance = p;
        addMouseListener(new MouseHandler());
    }

    /**
     * Note: This version assumes a uniform length of rows and columns.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
    	Seat[][] seats       = performance.seats();
    	Rectangle[][] rSeats = seats();
    	
    	Seat seat;
        for (int row = 0; row < rSeats.length; ++row)
        {
            for (int col = 0; col < rSeats[row].length; ++col)
            {
            	seat = seats[row][col];
            	
                // Filling color
                Color status;
                switch(seat.status)
                {
                    case Available: status = Color.GREEN; break;
                    case Booked:    status = Color.YELLOW; break;
                    case Sold:      status = Color.RED; break;
                    default:        status = Color.white;
                }

                // Draw seat
                Rectangle rect = rSeats[row][col];
                rect.width -= padding * 2;
                rect.height -= padding * 2;
                g.setColor(status);
                g.fillRect(rect.x,rect.y,rect.width,rect.height);
                g.setColor(Color.BLACK);
                g.drawRect(rect.x,rect.y,rect.width,rect.height);
            }
        }
    }


    /**
     * Calculates the rectangles of all seats within the JPanels’ bounds.
     * @return
     */
    private int padding = 2;
    public Rectangle[][] seats()
    {
        int rows = performance.seats().length;
        int cols = performance.seats()[0].length;
        
        Rectangle seat   = seat();
        Point     offset = offset();

        // Calculate all seats
        Rectangle[][] seats = new Rectangle[rows][cols];
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                Rectangle rect = new Rectangle();
                rect.x      = offset.x + seat.width * col;
                rect.y      = offset.y + seat.height * row;
                rect.width  = seat.width;
                rect.height = seat.height;
                seats[row][col] = rect;
            }
        }

        return seats;
    }
    
    /**
     * Calculates each Seats' maximum size.
     * @return
     */
    private Rectangle seat()
    {
        Rectangle seat   = new Rectangle(0, 0);
        seat.height     += getBounds().height / performance.seats().length;
        seat.width      += getBounds().width / performance.seats()[0].length;
        return seat;
    }
    
    /**
     * Calculate the offset from the JPanels' border to draw from.
     * @return
     */
    private Point offset()
    {
    	int rows = performance.seats().length, cols = performance.seats()[0].length;
        return new Point((getBounds().width - seat().width * cols) / 2,
        				 (getBounds().height - seat().height * rows) / 2);
    }
    
    /**
     * Finds the seat at the given X / Y coordinates.
     * 
     * @param x
     * @param y
     * @return
     */
    private Seat findSeat(Point p)
    {
    	// Find seat row
    	return new Seat();
    }

    private class MouseHandler implements MouseInputListener {
        public void mouseClicked(MouseEvent e)
        {
        	findSeat(e.getPoint());
            repaint();
        }
        
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
