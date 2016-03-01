package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class BeehiveSweeper extends JFrame{
	private static final int LOC_X = 150;
	private static final int LOC_Y = 300;
	private static final int Y_SEP = 60;
	private Font mainFont, selectedFont, normalFont;
	private Color normalColor, selectedColor, backgroundColor;
	int index = 0;
	String[] options = {"Start Bee Level", "Start Classic"}	;
	
	public BeehiveSweeper()
	{
		super();
	}

	public void init(){
		mainFont = new Font("Arial", Font.BOLD, 22);
		normalFont =  new Font("Arial", Font.BOLD, 12);
	}
	
	public void drawSelector(Graphics g)
	{
		g.setFont(normalFont);
		for(int i = 0; i < options.length; i++)
		{
			if (index == i) g.setColor(Color.YELLOW);
			else
				g.setColor(Color.white);
			
			drawText(options[i], LOC_X, LOC_Y + Y_SEP*i, g);
		}
	}
	
	public void drawText(String text, int x, int y, Graphics g)
	{
		g.drawString(text, x, y);
	}
	
	public void paintComponents(Graphics g)
	{
		this.setBackground(Color.BLACK);
		
		drawSelector(g);
	}
}
