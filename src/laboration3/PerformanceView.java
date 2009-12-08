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
        for (Seat[] row : seats())
        {
            for (Seat seat : row)
            {
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
                Rectangle rect = seat.rectangle;
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
    public Seat[][] seats()
    {
        int rows = performance.seats().length;
        int cols = performance.seats()[0].length;

        // Calculate each seats’ dimensions
        int margin  = 0; // space to canvas ends
        int padding = 2; // space between seats
        
        // Available canvas space
        Rectangle canvas = getBounds();
        canvas.width    -= margin * 2;
        canvas.height   -= margin * 2;
        canvas.setLocation(margin, margin);

        // Maximum seat size
        Rectangle seat   = new Rectangle(0, 0);
        seat.width      += canvas.width / cols;
        seat.height     += canvas.height / rows;

        // Rounding errors! D:
        canvas.x        += (canvas.width - seat.width * cols) / 2;
        canvas.y        += (canvas.height - seat.height * rows) / 2;

        // Calculate all seats
        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < cols; ++col)
            {
                Rectangle rect = new Rectangle();
                rect.x      = canvas.x + seat.width * col + padding;
                rect.y      = canvas.y + seat.height * row + padding;
                rect.width  = seat.width - padding * 2;
                rect.height = seat.height - padding * 2;
                performance.seats()[row][col].rectangle = rect;
            }
        }

        return performance.seats();
    }

    private class MouseHandler implements MouseInputListener {
        public void mouseClicked(MouseEvent e)
        {
            for (Seat[] row : seats())
            {
                for (Seat seat : row)
                {
                    if (seat.rectangle.contains(e.getPoint()))
                    {
                        seat.status = Seat.Status.Sold;
                    }
                }
            }
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
