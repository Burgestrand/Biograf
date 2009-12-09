package laboration3;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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

    /**
     * The actual performance this is a view for.
     */
    private Performance performance;
    private int rows, cols;

    /**
     * The list of marked seats
     */
    private ArrayList<Seat> marked;

    /**
     * @param p
     */
    public PerformanceView(Performance p)
    {
        performance(p);

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    /**
     * Sets the performance object to be used.
     * @param p
     */
    public void performance(Performance p)
    {
        performance = p;
        rows = p.seats().length;
        cols = p.seats()[0].length;
        marked = new ArrayList(cols);
    }

    /**
     * Note: This version assumes a uniform length of rows and columns.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)
    {
        // Clear drawing area (needed when performance object changes)
        g.clearRect(0, 0, getBounds().width, getBounds().height);

        Rectangle seat   = seat();
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

                Rectangle rect = new Rectangle();
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
                int border = marked.contains(seats[row][col]) ? 2 : 1;
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
     * Calculates the maximum size for a Seat
     * @return
     */
    private Rectangle seat()
    {
        Rectangle seat   = new Rectangle(0, 0);
        seat.height     += getBounds().height / rows;
        seat.width      += getBounds().width / cols;
        return seat;
    }
    
    /**
     * Calculate the virtual canvas to draw inside (offsets + dimension)
     * @return
     */
    private Rectangle canvas()
    {
        Dimension size = new Dimension(seat().width * cols, seat().height * rows);
        Point offset = new Point((getBounds().width - size.width) / 2,
                                 (getBounds().height - size.height) / 2);
        return new Rectangle(offset, size);
    }

    /**
     * Finds the Seat at the given coordinates. Returns “null” if no seat.
     * @param p
     * @return
     */
    private Seat findSeat(Point p)
    {
        Point base = new Point(p.x - canvas().x, p.y - canvas().y);

        int col = base.x / seat().width;
        int row = base.y / seat().height;

        return col < cols && row < rows ? performance.seats()[row][col] : null;
    }

    /**
     * Retrieves the marked seats.
     * @return
     */
    public ArrayList<Seat> marked()
    {
        return marked;
    }

    /**
     * Sets the status of all marked seats.
     * @param status
     */
    public void status(Seat.Status status)
    {
        for (Seat seat : marked)
        {
            seat.status(status);
        }
        marked.clear();
        repaint();
    }

    private class MouseHandler implements MouseInputListener {
        public void mouseClicked(MouseEvent e) {
            marked.clear();
            marked.add(findSeat(e.getPoint()));
            repaint();
        }

        public void mouseDragged(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }
}
