/**
 * Huvudprogrammet.
 */
package laboration3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import sun.awt.HorizBagLayout;
import sun.awt.VerticalBagLayout;

/**
 * @author Kim Burgestrand
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frmMain = new JFrame("Biograf");
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setPreferredSize(new Dimension(500, 500));

        // Stolplatser
        //----------------------------------------------------------------------
        Performance p = new Performance(12, 10);
        final PerformanceView pnlPerformance = new PerformanceView(p);

        // Kommandoknappar
        //----------------------------------------------------------------------
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(1, 4));

        JButton btnExit = new JButton("Avsluta");
        JButton btnBook = new JButton("Boka");
        JButton btnSell = new JButton("Sälj");
        JButton btnFetch = new JButton("Hämta");

        pnlButtons.add(btnExit);
        pnlButtons.add(btnBook);
        pnlButtons.add(btnSell);
        pnlButtons.add(btnFetch);

        // Händelsehanterare
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        btnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                pnlPerformance.status(Seat.Status.Booked);
            }
        });

        btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                boolean booked = false;
                for (Seat seat : pnlPerformance.marked())
                {
                    booked = seat.status().equals(Seat.Status.Booked);
                    if (booked == false)
                    {
                        break;
                    }
                }

                if (booked)
                {
                    pnlPerformance.status(Seat.Status.Sold);
                }
            }
        });

        btnFetch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        // Filmpanel
        //----------------------------------------------------------------------
        JPanel pnlFilm = new JPanel();
        JComboBox cmbMovie = new JComboBox();

        // Ladda in filmer
        for (Movie movie : new Repertoir().movies())
        {
            cmbMovie.addItem(movie);
        }

        cmbMovie.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e)
            {
                pnlPerformance.performance(((Movie) e.getItem()).performance());
                pnlPerformance.repaint();
            }
        });

        pnlFilm.setLayout(new GridLayout(1, 1));
        pnlFilm.add(cmbMovie);

        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new GridLayout(1, 2));
        pnlControls.add(pnlButtons);
        pnlControls.add(pnlFilm);
        pnlControls.setBorder(new EmptyBorder(5, 2, 5, 2));

        // Lägg till huvudpanelen
        //---------------------------------------------------------
        frmMain.add("Center", pnlPerformance);
        frmMain.add("South", pnlControls);
        frmMain.pack();
        frmMain.setVisible(true);
    }
}
