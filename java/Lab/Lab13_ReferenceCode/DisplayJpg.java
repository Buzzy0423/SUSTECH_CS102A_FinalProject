

import javax.swing.*;
import java.awt.*;

public class DisplayJpg {
    public static void main(String[] args)
    {
        JFrame window=new JFrame(); //create a Frame
        ImageIcon picture=new ImageIcon("Test.jpg"); //load a picture from computer
//        JLabel label=new JLabel(picture); //add the picture to a label
        window.setLayout(null);

        JButton button =new JButton(picture);
        button.setSize(100,50);
        button.setPreferredSize(new Dimension(100,20));
        button.setLocation(10,40);
        window.getContentPane().add(button);


//        window.getContentPane().add(label); //add the label to the frame
        window.setVisible(true); //Set the window to visible
        window.setSize(400,400); //set the size of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //let the window can be close by click "x"
    }

}
