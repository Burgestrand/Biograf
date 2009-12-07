/**
 * Huvudprogrammet.
 */
package laboration3;

import java.awt.*;
import javax.swing.*;

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

        Performance p = new Performance(10, 30);
        p.seats(5,26).status = Seat.Status.Sold;
        PerformanceView pPanel = new PerformanceView(p);
        frmMain.add(pPanel);

        frmMain.pack();
        frmMain.setVisible(true);
    }
}
