package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bl.BlController;
import bl.Level;


/**
 * 排名面板
 * @author 侍硕
 *
 */
public class rankPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int LBW=60;
	private static int LBH=35;
	
	
	private BlController controller;
	//进行中的级别列表
    private ArrayList<Level>  levelList;
    
	private Font font;
	private JComboBox<String> levelBox ;
	private JLabel levelLB;
	private JTable table ;
	private MyTableModel model;
    private JScrollPane Spane;
    private JButton exportBT;
    
	public rankPanel(BlController controller){
		    super();
		    this.setLayout(null);
		    this.controller  =controller;
		    this.levelList = this.controller.getSelectedLevels();
	}
	
   public void initComponent(){
	     
	     this.font = new Font("Microsoft YaHei UI", Font.PLAIN, 14);
	     this.levelLB = new JLabel("级别");
	     levelLB.setFont(font);
	     levelLB.setBounds(50,20,LBW,LBH);
	     initComboBox();
	     initTable();
	     this.exportBT = new JButton("导出");
	     this.exportBT.setBounds(300,250,LBW,LBH);
	     
	     
	     this.add(levelLB);   this.add(levelBox);
	     this.add(Spane);     this.add(exportBT);
   }
   
   private void initComboBox(){
	     String []   levelNames = new String [ levelList.size() ];
	     
	     for(int i=0;i<levelList.size();i++){
	    	   levelNames[i]  =  levelList.get(i).getLevelName();
	     }
	     
	     levelBox = new JComboBox<String>(levelNames);
	     levelBox.setBounds(levelLB.getX()+LBW,levelLB.getY(),200,LBH);
	     levelBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//更新表格
					 int index = levelBox.getSelectedIndex();
					 model.setData(levelList.get(index).getAltheteList());
				}
				
			});
   }
   
   //默认显示级别列表中第一级别的排名
   private void initTable(){
	   this.model = new MyTableModel(levelList.get(0).getAltheteList());
	   this.table  = new JTable(model);
	   Spane = new JScrollPane(table);
	   MyTableHandler.decorateTableAndSpane(table, Spane);
   }
   
}
