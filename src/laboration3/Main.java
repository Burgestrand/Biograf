/**
 * Huvudprogrammet.
 */
package laboration3;

import java.awt.*;
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
        Performance p = new Performance(12, 20);
        PerformanceView pnlPerformance = new PerformanceView(p);

        // Kontroller
        //-------------------------------------------------------
        // Filminfo
        JPanel pnlFilm = new JPanel();
        JLabel lblFilm = new JLabel("Mini Ninjas");
        pnlFilm.setLayout(new GridLayout(2, 1));
        pnlFilm.add(lblFilm);

        // Knappar
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new GridLayout(1, 2));
        JButton btnBook = new JButton("Boka");
        JButton btnBuy  = new JButton("Hämta");
        pnlButtons.add(btnBook);
        pnlButtons.add(btnBuy);

        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new GridLayout(1, 2));
        pnlControls.add(pnlFilm);
        pnlControls.add(pnlButtons);
        pnlControls.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Lägg till huvudpanelen
        //---------------------------------------------------------
        frmMain.add("Center", pnlPerformance);
        frmMain.add("South", pnlControls);
        frmMain.pack();
        frmMain.setVisible(true);
    }
}
