package laboration3;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import laboration3.Models.*;

/**
 * The main application entry point. Sets up everything from movies to GUI.
 * @author Kim Burgestrand
 */
@SuppressWarnings("serial")
public class Main extends JFrame
{
    public Main()
    {
        setPreferredSize(new Dimension(650, 500));
        setTitle("Biograf :: Laboration 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        View view = new View();
        Controller controller = new Controller(view);

        // add components to main frame
        //---------------------------------------------------------
        add("Center", view);
        add("South", controller);
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
    }

    /**
     * Listens for movie change.
     */
    class MovieChangeHandler implements ItemListener
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
    class PerformanceChangeHandler implements ItemListener
    {
        private View view;

        /**
         * @param performances  The combo box that contains the available performances
         * @param viewpanel     The performance view panel
         */
        public PerformanceChangeHandler(View view)
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
