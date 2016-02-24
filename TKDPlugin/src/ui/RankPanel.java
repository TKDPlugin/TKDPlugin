package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import bl.BlController;
import bl.Level;
import ui.ExportExcelUtil;


/**
 * 排名面板
 * @author 侍硕
 */
public class RankPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int LBW=60;
	private static int LBH=30;
	private static int showW=360;
	private static int showH=170;
	
	private BlController controller;
	//进行中的级别列表
    private ArrayList<Level>  levelList;
    private ExcelHandler excelHandler;
    
	private Font font;
	private JComboBox<String> levelBox ;
	private JLabel levelLB;
	private JTable table ;
	private MyTableRender  myTableRender;
	private MyTableModel model;
    private JScrollPane Spane;
    private JButton exportBT;
    private JButton selectAllBT;
    private JButton printBT;
    
	public RankPanel(BlController controller){
	
		     this.setLayout(null);
		     this.controller  =controller;
		     this.levelList = this.controller.getSelectedLevels();
		     initComponent();
	}
	

	
   public void initComponent(){
	     
	     this.font = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
	     this.levelLB = new JLabel("级别");
	     levelLB.setFont(font);
	     levelLB.setBounds(120,10,LBW,LBH);
	     initComboBox();
	     initTable();
	     
	     this.selectAllBT = new JButton("全选");
	     this.selectAllBT.setBounds(100,250,LBW,LBH);
	     this.selectAllBT.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					myTableRender.setAllSelected();
					repaint();
				}
		    	 
		     });
	     
	     
	     this.exportBT = new JButton("导出");
	     this.exportBT.setBounds(selectAllBT.getX()+LBW+20,selectAllBT.getY(),LBW,LBH);
	     this.exportBT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				 String path  =   ExportExcelUtil.getPath();
				 excelHandler = new ExcelHandler(path);
			     excelHandler.exportRankExcel(table);
			}
	    	 
	     });
	     
	     this.printBT = new JButton("打印");
	     this.printBT.setBounds(exportBT.getX()+LBW+20,selectAllBT.getY(),LBW,LBH);
	     this.printBT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				 
			}
	    	 
	     });

	     this.add(levelLB);   this.add(levelBox);
	     this.add(Spane);     this.add(exportBT);  this.add(selectAllBT);  this.add(printBT);
   }
   
   private void initComboBox(){
	     String []   levelNames = new String [ levelList.size() ];
	     
	     for(int i=0;i<levelList.size();i++){
	    	   levelNames[i]  =  levelList.get(i).getLevelName();
	     }
	     
	     levelBox = new JComboBox<String>(levelNames);
	     levelBox.setBounds(levelLB.getX()+LBW,levelLB.getY(),150,LBH);
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
	   this.myTableRender = new MyTableRender();
	   this.model = new MyTableModel(levelList.get(0).getAltheteList());
	   this.table  = new JTable(model);
	
	   Spane = new JScrollPane(table);
	   MyTableHandler.decorateTableAndSpane(table, myTableRender,Spane);
	   Spane.setBounds(45,50,showW,showH);
   }
   
}
