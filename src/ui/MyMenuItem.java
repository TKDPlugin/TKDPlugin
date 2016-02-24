package ui;

import java.awt.Graphics;

import javax.swing.JMenuItem;

public class MyMenuItem extends JMenuItem{
	
	public MyMenuItem(String s) {
		super(s);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(getForeground());
		g.setFont(getFont());
		g.drawString(getText(), 15, 15);
	}
	
}
