package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int Width=800;
	private static int Height=600;

			public   MainFrame(){
				this.initComponent();
            	MainPanel  mainPanel = new MainPanel();
            	this.getContentPane().add(mainPanel);
             }
			
			
			public void initComponent(){
				setType(Type.UTILITY);
         		setTitle("Tool");
         		setResizable(false);
         		setFont(new Font("Calibri", Font.PLAIN, 14));
         		setBackground(Color.WHITE);
         		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         		setBounds(100, 100, Width		, Height);
         		setVisible(true);
			}
			
 }
