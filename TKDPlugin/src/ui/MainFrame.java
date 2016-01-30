package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int Width=400;
	private static int Height=300;
           
	        Font font ;
			public   MainFrame(){
				this.initComponent();
            	
             }
			
			
			public void initComponent(){
				setType(Type.UTILITY);
         		setTitle("Tool");
         		setResizable(false);
         		setFont(new Font("Calibri", Font.PLAIN, 14));
         		setBackground(Color.WHITE);
       
            	initBar();
            	
            	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
         		int screenW = (int)screensize.getWidth();
         		int screenH= (int)screensize.getHeight();
         		setBounds((screenW-Width)/2, (screenH-Height)/2, Width	, Height);
         		setVisible(true);
			}
			
			
			public void initBar(){
	        	   font  = new Font("Microsoft YaHei UI Light", Font.PLAIN, 14);
	        	   
	        		JMenuBar menuBar = new JMenuBar();
	        		menuBar.setBackground(Color.LIGHT_GRAY);
	        		menuBar.setBorderPainted(false);
	        		menuBar.setFont(font);
	        		setJMenuBar(menuBar);
	        		
	        		JMenu menu = new JMenu("功能选项");
	        		menu.setFont(font);
	        		menu.setHorizontalAlignment(SwingConstants.CENTER);
	        		menuBar.add(menu);
	        		
	        		JMenuItem rankItem = new JMenuItem("排名");
	        		rankItem.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent arg0) {
	        				
	        			}
	        		});
	        		rankItem.setFont(font);
	        		rankItem.setHorizontalAlignment(SwingConstants.CENTER);
	        		menu.add(rankItem);
	        		
	        		JMenuItem sumItem = new JMenuItem("汇总");
	        		sumItem.setFont(font);
	        		sumItem.setHorizontalAlignment(SwingConstants.CENTER);
	        		menu.add(sumItem);
	           }
 }
