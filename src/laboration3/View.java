package laboration3;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import laboration3.Models.Performance;
import laboration3.Models.Seat;

/**
 * Draws the performance in a JPanel.
 *
 * @author Kim Burgestrand
 */
@SuppressWarnings("serial")
public class View extends JPanel {
    /**
     * The actual performance this is a view for.
     */
    private Performance performance;
    private int rows, cols;

    /**
     * The list of marked seats
     */
    private ArrayList<Seat> selected;

    /**
     * Remember to call performance before you try to paint this panel!
     */
    public View()
    {
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    /**
     * Sets the performance object to be used.
     * @param p the performance object
     */
    public void performance(Performance p)
    {
        performance = p;
        rows = p.seats().length;
        cols = p.seats()[0].length;
        selected = new ArrayList<Seat>(cols);
        repaint();
    }

    /**
     * Paints the performance in a pretty way in the panel.
     * 
     * Note: This version assumes a uniform length of rows and columns.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
        // Clear drawing area (needed when performance object changes)
        g.clearRect(0, 0, getBounds().width, getBounds().height);

        Dimension seat   = seat();
    	Seat[][] seats   = performance.seats();
        int padding      = 2;
    	
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                Color status;
                switch(seats[row][col].status())
                {
                    case Available: status = Color.GREEN; break;
                    case Booked:    status = Color.YELLOW; break;
                    case Sold:      status = Color.RED; break;
                    default:        status = Color.WHITE;
                }

                Rectangle rect = new Rectangle(new Point(0, 0), seat);
                rect.x      = canvas().x + seat.width * col;
                rect.y      = canvas().y + seat.height * row;
                rect.width  = seat.width - padding * 2;
                rect.height = seat.height - padding * 2;

                // Border
                g.setColor(Color.BLACK);
                g.fillRect(rect.x
                          ,rect.y
                          ,rect.width
                          ,rect.height);

                // Filling (superficial border)
                int border = selected.contains(seats[row][col]) ? 2 : 1;
                g.setColor(status);
                g.fillRect(rect.x + border
                          ,rect.y + border
                          ,rect.width - border * 2
                          ,rect.height - border * 2);

                // Seat number
                g.setColor(Color.BLACK);
                String number = Integer.toString((cols * row) + col);
                Rectangle2D n = g.getFontMetrics().getStringBounds(number, g);
                g.drawString(number
                            ,rect.x + rect.width / 2 - (int)(n.getWidth() / 2)
                            ,rect.y + rect.height / 2 + (int)(n.getHeight() / 2));
            }
        }
    }

    /**
     * @return  Maximum dimensions for any given seat in this panel
     */
    private Dimension seat()
    {
        Dimension seat   = new Dimension(0, 0);
        seat.height     += getBounds().height / rows;
        seat.width      += getBounds().width / cols;
        return seat;
    }
    
    /**
     * @return  The virtual canvas to draw inside (both offsets and dimensions)
     */
    private Rectangle canvas()
    {
        Dimension size = new Dimension(seat().width * cols, seat().height * rows);
        Point offset = new Point((getBounds().width - size.width) / 2,
                                 (getBounds().height - size.height) / 2);
        return new Rectangle(offset, size);
    }

    /**
     * @param p	Coordinates relative to this panel
     * @return  The seat at the given coordinates, or null if no seat was found
     */
    private Seat findSeat(Point p)
    {
        Point base = new Point(p.x - canvas().x, p.y - canvas().y);

        int col = base.x / seat().width;
        int row = base.y / seat().height;

        return col < cols && row < rows ? performance.seats()[row][col] : null;
    }

    /**
     * @return  A list of the marked seats
     */
    public ArrayList<Seat> selected()
    {
        return new ArrayList<Seat>(selected);
    }

    /**
     * Deselects all marked seats
     */
    public void deselect()
    {
        selected.clear();
        repaint();
    }

    /**
     * Unmarks the given seat.
     * 
     * Note: does *not* call repaint
     * 
     * @param seat
     */
    public void deselect(Seat seat)
    {
        selected.remove(seat);
    }

    /**
     * Handles mouse clicks on the whole panel.
     */
    private class MouseHandler extends MouseInputAdapter implements MouseInputListener {

        /**
         * Marks the seat that was clicked on, or unmarks it if it was already marked.
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            Seat seat = findSeat(e.getPoint());

            if ( ! selected.contains(seat))
            {
                selected.clear();
                selected.add(seat);
            }
            else
            {
                selected.clear();
            }
            
            repaint();
        }
    }
}
