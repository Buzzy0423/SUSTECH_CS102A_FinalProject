
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculation extends JFrame 
{
   private JButton plainJButton1; 
   private JButton plainJButton2; 
   private JButton plainJButton3; 
   private JButton plainJButton4; 
   private JButton plainJButton5; 
   private JButton plainJButton6;
   private JButton plainJButton7; 
   private JButton plainJButton8; 
   private JButton plainJButton9; 
   private JButton plainJButton0;
   private JButton plainJButtonAdd; 
   private JButton plainJButtonSub; 
   private JButton plainJButtonEq; 
   
   private JTextField answer;  

   private String operation1 = "";  
   private String operation2 = "";
   private String operator = "";

   // ButtonFrame adds JButtons to JFrame
   public Calculation()
   {
      super( "Calculation" );
      JPanel jp = new JPanel();
      jp.setLayout( new GridLayout(4,4) );

      plainJButton1 = new JButton( "1" ); 
      jp.add( plainJButton1 ); 

      plainJButton2 = new JButton( "2" ); 
      jp.add( plainJButton2 ); 
      
      plainJButton3 = new JButton( "3" ); 
      jp.add( plainJButton3 ); 
      
      plainJButton4 = new JButton( "4" ); 
      jp.add( plainJButton4 ); 
      
      plainJButton5 = new JButton( "5" ); 
      jp.add( plainJButton5 ); 

      plainJButton6 = new JButton( "6" ); 
      jp.add( plainJButton6 ); 

      plainJButton7 = new JButton( "7" ); 
      jp.add( plainJButton7 ); 

      plainJButton8 = new JButton( "8" ); 
      jp.add( plainJButton8 ); 
      
      plainJButton9 = new JButton( "9" ); 
      jp.add( plainJButton9 ); 
      
      plainJButton0 = new JButton( "0" ); 
      jp.add( plainJButton0 ); 
      
      plainJButtonAdd = new JButton( "+" ); 
      jp.add( plainJButtonAdd ); 
      
      plainJButtonSub = new JButton( "-" );
      jp.add( plainJButtonSub ); 
      
      plainJButtonEq = new JButton( "=" ); 
      jp.add( plainJButtonEq ); 
      
      add(jp, BorderLayout.SOUTH);
      
      answer = new JTextField("");
      answer.setEditable(false);
      answer.setHorizontalAlignment(JTextField.RIGHT);
      add(answer, BorderLayout.CENTER);
        

      // create new ButtonHandler for button event handling 
      ButtonHandler handler = new ButtonHandler();
      plainJButton1.addActionListener( handler );
      plainJButton2.addActionListener( handler );
      plainJButton3.addActionListener( handler );
      plainJButton4.addActionListener( handler );
      plainJButton5.addActionListener( handler );
      plainJButton6.addActionListener( handler );
      plainJButton7.addActionListener( handler );
      plainJButton8.addActionListener( handler );
      plainJButton9.addActionListener( handler );
      plainJButton0.addActionListener( handler );
      plainJButtonAdd.addActionListener( handler );
      plainJButtonSub.addActionListener( handler );
      plainJButtonEq.addActionListener( handler );
   } // end ButtonFrame constructor

	public int compute(String operation1, String operation2, String operator) {
		int a = Integer.parseInt(operation1);
		int b = Integer.parseInt(operation2);
		if (operator.charAt(0) == '+') {
			return a + b;
		} else {
			return a - b;
		}
	}
	  
   // inner class for button event handling
   private class ButtonHandler implements ActionListener 
   {
      // handle button event
      public void actionPerformed( ActionEvent event )
      {
    	if (( event.getSource() == plainJButtonAdd ) || ( event.getSource() == plainJButtonSub )) {
    		if (!operation1.equals("") && operation2.equals("")) {
    			operator = event.getActionCommand();
    		}
    		else if (!operation1.equals("") && !operation2.equals("")){

    			operation1 = Integer.toString(compute(operation1, operation2, operator));
    			answer.setText(operation1);
    			operation2 = "";
    			operator = event.getActionCommand();
    		}
    		else if (operation1.equals("") && !answer.getText().equals("")){
    			operation1 = answer.getText();
    			operator = event.getActionCommand();
    		}
    	} 
    	else if ( event.getSource() == plainJButtonEq ){
    		if (!operation1.equals("") && !operation2.equals("") && !operator.equals("") ){
    			answer.setText(Integer.toString(compute(operation1, operation2, operator)));
    			operation1 = "";
    			operation2 = "";
    			operator = "";
    		}
    		else{
    			answer.setText("");
    			operation1 = "";
    			operation2 = "";
    			operator = "";
    		}
    			
    	} 
    	else {
    		if (operator.equals("")) {
    			operation1 = operation1 + event.getActionCommand();
    			answer.setText(operation1);
    		} else {
    			operation2 = operation2 + event.getActionCommand();
    			answer.setText(operation2);
    		}
    	}
      } // end method actionPerformed
   } // end private inner class ButtonHandler
} // end class ButtonFrame

