package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * 主界面的面板
 */
public class MainPanel extends JPanel {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		   Font  font ;
           public MainPanel(){
        	   initComponent();
           }
           
           public void initComponent(){
        	   font  = new Font("Microsoft YaHei UI Light", Font.PLAIN, 14);
        	   
        		JMenuBar menuBar = new JMenuBar();
        		menuBar.setBackground(Color.LIGHT_GRAY);
        		menuBar.setBorderPainted(false);
        		menuBar.setFont(font);
        		this.add(menuBar);
        		
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
