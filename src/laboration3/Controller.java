package laboration3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import java.util.ArrayList;

import laboration3.Models.*;

/**
 * Main controller: acts on buttons and performs the appropriate
 * action on the models. This is a combined view / controller. 
 * 
 * Note:  En kontroll-klass (också en subklass till JPanel) som innehåller knapparna 
 * 	      och två JLabels för film och datum. Här finns knapplyssnare som inre klass(er).
 * 
 * @author Kim Burgestrand
 * @since  v2.0.1
 */
@SuppressWarnings("serial")
public class Controller extends JPanel implements ActionListener
{
	private View view;
	private JComboBox movies;
	private JComboBox performances;
	
	// No instantiating without arguments
	@SuppressWarnings("unused")
	private Controller() {}
	
	/**
	 * @param view the main view object
	 */
	public Controller(View view)
	{
		view(view);
		setLayout(new GridLayout());
		setBorder(new EmptyBorder(5, 2, 5, 2));
		initButtons();
		initMovies();
	}
	
	/**
	 * Creates the action buttons.
	 */
	private void initButtons()
	{
		JPanel pnl = new JPanel();
		JButton[] buttons = {
				new JButton("Stäng"),
				new JButton("Boka"),
				new JButton("Sälj"),
				new JButton("Hämta")
		};
		
		for (JButton button : buttons)
		{
			button.addActionListener(this);
			pnl.add(button);
		}
		
		add(pnl);
	}
	
	/**
	 * Creates the movie / performance selection combo boxes.
	 */
	private void initMovies()
	{
		JPanel pnl = new JPanel();
		ArrayList<Movie> movielist = Repertoir.Default().movies();
		Movie movie = movielist.get(0); // Currently selected movie is always first item
		
		// Movies combo box
		movies = new JComboBox(movielist.toArray());
		movies.setEditable(true);
        movies.addItemListener(new MovieChanger());
        
        // Performance combo box
        performances = new JComboBox(movie.performance().toArray());
        performances.setEditable(true);
        performances.addItemListener(new PerformanceChanger());
        
        view.performance((Performance)performances.getSelectedItem());
        
		// --
        pnl.add(movies);
        pnl.add(performances);
        add(pnl);
	}
	
	/**
	 * Sets the view this controller is to use.
	 * @param view the view object
	 */
	public void view(View view)
	{
		this.view = view;
	}
	
    /**
     * Prints a receipt to STDOUT.
     * @param seat
     */
    private void printReceipt(Seat seat)
    {
    	System.out.println(view.performance());
        System.out.println("\tPlats #" + (seat.col() + 1) + " på rad " + (seat.row() + 1) + " såld.");
    }
	
	/**
	 * Event handler for button clicks
	 * @param e 
	 */
	public void actionPerformed(ActionEvent e)
	{
        String text = ((JButton)e.getSource()).getText();

        if (text.equals("Stäng"))
        {
            System.exit(0);
        }
        else if (text.equals("Boka"))
        {
            // Books the seat(s) if they’re available
            for (Seat seat : view.selected())
            {
                if (seat.status().equals(Seat.Status.Available))
                {
                	seat.status(Seat.Status.Booked);
                	view.deselect(seat);
                }
            }
            
            view.repaint();
        }
        else if (text.equals("Sälj"))
        {
            // Sells the seat(s) if they’ve been booked
            for (Seat seat : view.selected())
            {
                if (seat.status().equals(Seat.Status.Booked))
                {
                    seat.status(Seat.Status.Sold);
                    printReceipt(seat);
                    view.deselect();
                }
            }
        }
	}
	
	/**
	 * Event handler for change of movie.
	 */
	class MovieChanger implements ItemListener
	{
		/**
		 * Allows selecting a new movie
		 * @param e
		 */
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() != ItemEvent.SELECTED) return;
			Object type = e.getItem();
			Movie movie = null;
			
			if (type instanceof Movie)
			{
				movie = (Movie)type;
				performances.removeAllItems();
				for (Performance p : movie.performance())
				{
					performances.addItem(p);
				}
			}
			else if (type instanceof String)
			{
				movie = new Movie(type.toString(), new ArrayList<Performance>());
				movies.addItem(movie);
				movies.setSelectedItem(movie);
			}
		}
	}
	
	/**
	 * Event handler for change of performance.
	 */
	class PerformanceChanger implements ItemListener
	{
		/**
		 * Allows selecting a new performance, or CREATING (!!) a performance
		 * @param e
		 */
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() != ItemEvent.SELECTED) return;
			Object type = e.getItem();
			
			if (type instanceof Performance)
			{
				view.performance((Performance)e.getItem());
			}
			else if (type instanceof String)
			{
				String time = type.toString();
				// Default size: 10x10
				Movie movie = (Movie) movies.getSelectedItem();
				Performance p = new Performance(new Salon(8, 8), time);
				movie.add(p);
				performances.addItem(p);
				performances.setSelectedItem(p);
			}
		}
	}
}
