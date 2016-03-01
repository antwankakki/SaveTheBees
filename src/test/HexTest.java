package test;

import graphics.HexButton;
import graphics.HexagonalLayout;
import mechanics.BeeCell;
import mechanics.BeeHive;
import mechanics.BeeHive19;
import mechanics.GeneralBeeHive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * Simple test class for HexagonalLayout.
 *
 * @author Darren Eck (but heavily borrowed from
 * Kristian Johansen)
 *
 */
public class HexTest implements ActionListener, ComponentListener{

	
	BeeHive hive;
	JFrame f;
	HexButton[] buttons;
	int flagCounter = 0;
	
	int midColumnSize = 15;
	int mines = 40;
	 
	
	public HexTest()
	{
		
		f = new JFrame();
		JPanel contentPanel = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(10,10,0,0);
		contentPanel.setBorder(padding);
		contentPanel.addComponentListener(this);
		f.setContentPane(contentPanel);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setPreferredSize(new Dimension(455,600));
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.getContentPane().setBackground(Color.decode("#FFE7AA"));
		f.getContentPane().setForeground(Color.decode("#FFE7AA"));
		
		hive = new GeneralBeeHive(midColumnSize, mines);
		//hive = new BeeHive19(mines);
		
		// MAKE IT HEXAGONAL BABY!!
		HexagonalLayout hl = new HexagonalLayout(midColumnSize, new Insets(1,1,1,1));

		f.setLayout(hl);
		buttons = new HexButton[hl.numOfCells()];
		for (int i = 0; i < hl.numOfCells(); i++) { // Change the number in the loop to get
						// more/less buttons
		    HexButton b = new HexButton(i);
			buttons[i] = b;
		    b.addActionListener(this);
		    
		    f.add(b);
		}

		f.setVisible(true);
	}
	
    public static void main(String[] args) {
    	new HexTest();
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		HexButton a = (HexButton) arg0.getSource();
	    a.setBackground(Color.WHITE);
	    BeeCell cell = hive.getCell(a.getID());
	    exploreAround(cell, 5);
	   // exploreAround(cell);

	}
	
	private void exploreAround(BeeCell cell, int revealCounter)
	{	
		if (revealCounter == 0)
		{
			return;
		}
		else
			revealCounter--;
		
		cell.setVisited(true);
		System.out.print("Exploring cell "+ cell.getID() + "...");
		
		int status = cell.getStatus();
		HexButton a = buttons[cell.getID()];
		
	    if (status == BeeCell.PESTICIDE)
	    	a.setError();
	    else if (status == BeeCell.NORMAL)
	    	a.setHappy();
	    else if (status == BeeCell.EMPTY)
	    {
	    	a.setOpened();
	    }
	    else if (status == BeeCell.VERY_HAPPY)
	    	a.setVeryHappy();
	    else if (status == BeeCell.SICK)
	    	a.setSick();
	    a.setText(String.valueOf(status));
	    try 
        {
            Image img = ImageIO.read(getClass().getResource("/unopened.gif"));
            a.setIcon(new ImageIcon(img));
            a.setHorizontalTextPosition(JButton.CENTER);
            a.setVerticalTextPosition(JButton.CENTER);
        } 
        catch (IOException ex) {}
	    
	    for (BeeCell n : cell.neighbors)
	    {
	    	if (n == null) continue;
	    	if (n.getStatus() != BeeCell.PESTICIDE && n.getVisisted() == false)
	    		exploreAround(n, revealCounter);
	    } 
	}

	private void exploreAround(BeeCell cell)
	{
		/*Delete after test*/
		for (BeeCell n : cell.neighbors)
			if (n != null) buttons[n.getID()].highlight();
		buttons[cell.getID()].highlight();
		return;

	}

	private void userLost() {
		// TODO Auto-generated method stub
		setWarningMsg("YOU FUCKNIG LOSER!!!");
		System.out.println("width height: " + f.getWidth() + " " + f.getHeight());
	}
	


	public static void setWarningMsg(String text){
	    Toolkit.getDefaultToolkit().beep();
	    JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
	    JDialog dialog = optionPane.createDialog("Warning!");
	    dialog.setAlwaysOnTop(true);
	    dialog.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


}