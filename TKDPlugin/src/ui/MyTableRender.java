package ui;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class MyTableRender extends DefaultTableCellRenderer {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	// 浅蓝色0 ,191 ,255,80
	final Color bgColor1 = new Color(0, 191, 255, 80);
	// 白色
	final Color bgColor2 = new Color(255, 255, 255, 100);
	// 蓝色
	final Color headerColor = new Color(0, 191, 255, 180);

	private JCheckBox[] checkBoxs = null;

	public MyTableRender() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (row % 2 == 0) {
			setBackground(bgColor2);
		} else if (row % 2 == 1) {
			setBackground(bgColor1);
		} else {
			setBackground(headerColor);
		}

		if (column == table.getColumnCount()-1&&row!=-1) {
			
			if (checkBoxs == null) {
				initBoxs(table.getRowCount()+1);
			}
			
			JCheckBox bt = checkBoxs[row + 1];
			if (hasFocus) {

				if (bt.isSelected()) {
					bt.setSelected(false);
				} else {
					bt.setSelected(true);
				}
			}
			return bt;
		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	}

	private void initBoxs(int size) {
		checkBoxs = new JCheckBox[size];
		
		for (int i = 0; i < size; i++) {
			checkBoxs[i] = new JCheckBox();
			checkBoxs[i].setHorizontalAlignment(JCheckBox.CENTER);
		}
	}
	
	public void setAllSelected(){
		for (int i = 0; i < checkBoxs.length; i++) {
			checkBoxs[i].setSelected(true);
		}
	}

	
	public JCheckBox[]  getCheckBoxs(){
		return this.checkBoxs;
	}
	

}
