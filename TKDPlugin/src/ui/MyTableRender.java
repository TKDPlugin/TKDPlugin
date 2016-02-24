package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class MyTableRender extends DefaultTableCellRenderer{
	
	JCheckBox[] boxs;
	//浅蓝色0 ,191 ,255,80
  	final Color   bgColor1 = new Color(0 ,191 ,255,80);
  	//白色
  	final Color bgColor2 = new Color(255, 255 ,255,100);
  //蓝色
		final Color  headerColor = new Color(0 ,191 ,255,180);
	
	public JCheckBox[] getBoxs() {
		return boxs;
	}

   
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
			if (row % 2 == 0){
					setBackground(bgColor2);
			    }
				else if(row % 2 == 1){
					setBackground(bgColor1);
				}
				else{
					
					setBackground(headerColor);
				}
			
			
			if(column==table.getColumnCount()-1){
				if(boxs==null){
					initBoxs(table.getRowCount()+1);
				}
				
					 return boxs[row+1];
				 
				 
			}
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
	private void initBoxs(int size){
		boxs = new JCheckBox[size];
		for(int i=0;i<size;i++){
			boxs[i] = new JCheckBox();
		}
	}
}
