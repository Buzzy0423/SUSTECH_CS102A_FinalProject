

// Testing ButtonFrame.
import javax.swing.JFrame;

public class CalculationTest 
{
   public static void main( String[] args )
   { 
	   Calculation calculationFrame = new Calculation(); // create ButtonFrame
	   calculationFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	   calculationFrame.setLocationRelativeTo(null);
	   calculationFrame.pack(); // set frame size
	   calculationFrame.setVisible( true ); // display frame
   } // end main
} // end class ButtonTest

