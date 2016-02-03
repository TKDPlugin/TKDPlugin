package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import bl.BlController;

public class SumPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlController controller;
	
	  public SumPanel (BlController blcontroller){
			this.setLayout(null);
		}
		
		public void paintComponent(Graphics g){
			   super.paintComponent(g);
			   g.setColor(Color.WHITE);
			   g.fillRect(0, 0, 400, 300);
			
		}
	
       
}
