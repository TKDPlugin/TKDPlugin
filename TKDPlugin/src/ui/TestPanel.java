package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TestPanel extends JPanel {
	public TestPanel(){
		this.setLayout(null);
	}
	
	public void paintComponent(Graphics g){
		   super.paintComponent(g);
		   g.setColor(Color.black);
		   g.fillRect(0, 0, 400, 300);
		
	}
}
