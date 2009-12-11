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
public class Controller extends JPanel implements ActionListener, ItemListener
{
	private View view;
	private JComboBox movies;
	private JComboBox performances;
	
	// No instantiating without arguments
	@SuppressWarnings("unused")
	private Controller() {}
	
	/**
	 * @param view
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
		Performance perf = movie.performance().get(0);
		
		movies = new JComboBox(movielist.toArray());
        movies.addItemListener(this);
        performances = new JComboBox(movie.performance().toArray());;
        performances.addItemListener(this);  
        pnl.add(movies);
        pnl.add(performances);
        
        view.performance(perf);
        
        add(pnl);
	}
	
	/**
	 * Sets the view this controller is to use.
	 * @param view
	 */
	public void view(View v)
	{
		view = v;
	}
	
    /**
     * Prints a receipt to STDOUT.
     * @param seat
     */
    private void printReceipt(Seat seat)
    {
        System.out.println("Plats #" + (seat.col() + 1) + " på rad " + (seat.row() + 1) + " såld.");
    }
	
	/**
	 * Called when a button is clicked.
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
            for (Seat seat : view.marked())
            {
                if (seat.status().equals(Seat.Status.Available))
                {
                	seat.status(Seat.Status.Booked);
                	view.unmark(seat);
                }
            }
            
            view.repaint();
        }
        else if (text.equals("Sälj"))
        {
            // Sells the seat(s) if they’ve been booked
            for (Seat seat : view.marked())
            {
                if (seat.status().equals(Seat.Status.Booked))
                {
                    seat.status(Seat.Status.Sold);
                    printReceipt(seat);
                    view.unmark();
                }
            }
        }
	}

	/**
	 * Event handler for change of movie / performance.
	 * @param e
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() != ItemEvent.SELECTED) return;
		
		// Movie or Performance
		Object type = e.getItem();
		
		if (type instanceof Movie)
		{
			Movie movie = (Movie) e.getItem();
			performances.removeAllItems();
			
			for (Performance p : movie.performance())
			{
				performances.addItem(p);
			}
		}
		else if (type instanceof Performance)
		{
			Performance perf = (Performance) e.getItem();
			view.performance(perf);
		}
	}
}
