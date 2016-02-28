package test;

import graphics.HexButton;
import graphics.HexagonalLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Simple test class for HexagonalLayout.
 *
 * @author Darren Eck (but heavily borrowed from
 * Kristian Johansen)
 *
 */
public class HexTest {

    public static void main(String[] args) {
	JFrame f = new JFrame();
//	f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	f.setPreferredSize(new Dimension(455,600));
	f.setResizable(false);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.pack();

	
	
	//Change insets and columns here.
	//The flag indicates wether or not to begin with a small row.
	//
	//Rows are calculated automaticaly, based on number of columns/elements
//	f.setLayout(new HexagonalLayout(6, new Insets(1, 1, 1, 1), false));
//
//	for (int i = 0; i < 44; i++) { // Change the number in the loop to get
//					// more/less buttons
//
//	    HexButton b = new HexButton();
//	    b.setBackground(Color.blue);
//
//	    //"Random" color actionlistener, just for fun.
//	    b.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//		    JButton a = (JButton) e.getSource();
//		    a.setBackground(Color.WHITE);
//		}
//
//	    });
//	    
//	    f.add(b);
//	}
//
//	f.setVisible(true);
//
//    }
	
	
	
	// MAKE IT HEXAGONAL BABY!!
	HexagonalLayout hl = new HexagonalLayout(21, new Insets(1,1,1,1));
	f.setLayout(hl);

	for (int i = 0; i < hl.numOfCells(); i++) { // Change the number in the loop to get
					// more/less buttons

	    HexButton b = new HexButton();
	    b.setBackground(Color.blue);
	    ImageIcon img = new ImageIcon("/flag.png");
	    b.setIcon(img);

	    //"Random" color actionlistener, just for fun.
	    b.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    JButton a = (JButton) e.getSource();
		    a.setBackground(Color.WHITE);
		    a.setText("5");
		    try 
	        {
	            Image img = ImageIO.read(getClass().getResource("/unopened.gif"));
	            a.setIcon(new ImageIcon(img));
	            a.setHorizontalTextPosition(JButton.CENTER);
	            a.setVerticalTextPosition(JButton.CENTER);
	        } 
	        catch (IOException ex) {}
		}

	    });
	    
	    f.add(b);
	}

	f.setVisible(true);

    }
}