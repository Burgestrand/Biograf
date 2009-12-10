/**
 * Huvudprogrammet.
 */
package laboration3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Kim Burgestrand
 */
public class Main extends JFrame
{
    public Main()
    {
        setPreferredSize(new Dimension(500, 500));
        setTitle("Biograf :: Laboration 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Repertoir repertoir = Repertoir.Default();
        PerformanceView pnlPerformance = new PerformanceView();

        // Kommandoknappar
        //----------------------------------------------------------------------
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout());

        ButtonHandler btnHandler = new ButtonHandler(pnlPerformance);

        pnlButtons.add(new MyButton("Avsluta", btnHandler));
        pnlButtons.add(new MyButton("Boka", btnHandler));
        pnlButtons.add(new MyButton("Sälj", btnHandler));
        pnlButtons.add(new MyButton("Hämta", btnHandler));

        // Film panel
        //----------------------------------------------------------------------
        JPanel pnlFilm = new JPanel();

        JComboBox cmbMovie = new JComboBox(repertoir.movies().toArray());
        Movie current = (Movie)cmbMovie.getSelectedItem();
        JComboBox cmbPerformances = new JComboBox(current.performance().toArray());
        pnlPerformance.performance((Performance) cmbPerformances.getSelectedItem());

        // Event handlers
        cmbMovie.addItemListener(new MovieChangeHandler(cmbPerformances));
        cmbPerformances.addItemListener(new PerformanceChangeHandler(pnlPerformance));

        pnlFilm.setLayout(new GridLayout(1, 2));
        pnlFilm.add(cmbMovie);
        pnlFilm.add(cmbPerformances);

        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new GridLayout(1, 2));
        pnlControls.add(pnlButtons);
        pnlControls.add(pnlFilm);
        pnlControls.setBorder(new EmptyBorder(5, 2, 5, 2));

        // add components to main frame
        //---------------------------------------------------------
        add("Center", pnlPerformance);
        add("South", pnlControls);
        pack();
        setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new Main();
    }

    // Knappar
    //--------------------------------------------------------------------------
    /**
     * This kind of button implements its’ own action handler.
     */
    private class MyButton extends JButton
    {
        /**
         * Creates a JButton with the given action listener.
         * @param text
         * @param handler
         */
        public MyButton(String text, ActionListener handler)
        {
            setText(text);
            addActionListener(handler);
        }
    }

    /**
     * Handles button clicking.
     */
    private class ButtonHandler implements ActionListener
    {
        private PerformanceView performance;

        public ButtonHandler(PerformanceView pnl)
        {
            performance = pnl;
        }

        public void actionPerformed(ActionEvent e)
        {
            String text = ((MyButton)e.getSource()).getText();

            if (text.equals("Avsluta"))
            {
                System.exit(0);
            }
            else if (text.equals("Boka"))
            {
                // Books the seat(s) if they’re available
                for (Seat seat : performance.marked())
                {
                    if (seat.status().equals(Seat.Status.Available))
                    {
                        performance.status(Seat.Status.Booked);
                        performance.unmark(seat);
                    }
                }
            }
            else if (text.equals("Sälj"))
            {
                // Sells the seat(s) if they’ve been booked
                for (Seat seat : performance.marked())
                {
                    if (seat.status().equals(Seat.Status.Booked))
                    {
                        seat.status(Seat.Status.Sold);
                        printReceipt(seat);
                        performance.unmark();
                    }
                }
            }
        }

        /**
         * Prints a receipt to STDOUT.
         * @param seat
         */
        private void printReceipt(Seat seat)
        {
            System.out.println("Plats #" + (seat.col() + 1) + " på rad " + (seat.row() + 1) + " såld.");
        }
    }

    /**
     * Listens for movie change.
     */
    private class MovieChangeHandler implements ItemListener
    {
        private JComboBox performances;

        /**
         * @param performances  The combo box which contains all performances.
         */
        public MovieChangeHandler(JComboBox performances)
        {
            this.performances = performances;
        }

        /**
         * Fired when the movie is changed.
         * @param e
         */
        public void itemStateChanged(ItemEvent e)
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                Movie movie = (Movie)e.getItem();
                performances.removeAllItems();

                for (Performance p : movie.performance())
                {
                    performances.addItem(p);
                }
            }
        }

    }

    /**
     * Listens for performance change.
     */
    private class PerformanceChangeHandler implements ItemListener
    {
        private PerformanceView view;

        /**
         * @param performances  The combo box that contains the available performances
         * @param viewpanel     The performance view panel
         */
        public PerformanceChangeHandler(PerformanceView view)
        {
            this.view = view;
        }

        /**
         * Handles change of performance.
         * @param e
         */
        public void itemStateChanged(ItemEvent e)
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                Performance p = (Performance) e.getItem();
                view.performance(p);
            }
        }
    }
}
