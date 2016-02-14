package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hxtt.concurrent.w;

import bl.BlController;

public class SumPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int LBW=150;
	private static int LBH =40;
	
	private BlController controller;
	private ExcelHandler excelHandler;
	
	private Font font;
	private JButton exportBT;
	
	  public SumPanel (BlController blcontroller){
			this.setLayout(null);
			this.controller = blcontroller;
			this.initComponent();
			
		}
	  
	  public void initComponent(){
		   this.font = new Font("Microsoft YaHei UI", Font.PLAIN, 20);
		   exportBT = new JButton("导出汇总表格");
		   exportBT.setBounds((450-LBW)/2,(350-LBH)/2,LBW,LBH);
		   exportBT.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String path  =   ExportExcelUtil.getPath();
				excelHandler = new ExcelHandler(path);
				excelHandler.exportExcel();
			}
			   
		   });
		   this.add(exportBT);
	  }
		
		
       
}
