package ui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.table.AbstractTableModel;

import bl.Athlete;


/**
 * 排名列表需要用到的model，内含需要显示的数据，
 * 即每个运动员的姓名、队伍和名次
 * @author 侍硕
 *
 */
public class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	  /**
	    * 用于表格显示的历史付款单概要信息列表
	    */

	   private  ArrayList<Athlete> athleteList;
	   private Vector<String> title=null;
	   private Vector<Vector<Object>> data=null;
	   
		public  MyTableModel(ArrayList<Athlete> athleteList){
			   super();
			   this.init ( athleteList);
		}
		
		public void init(ArrayList<Athlete> athleteList){
			  title = new Vector<String>();
			  data = new Vector<Vector<Object>>();
			  title.add("名次");
			  title.add("姓名");
			  title.add("队伍");
			  title.add("级别");
			  title.add("选择");
			  this.setData(athleteList);
		}
	
		
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return  title.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex,	 int columnIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex).get(columnIndex);
	}
   
	@Override
	public String getColumnName(int arg0) {
		
		return title.get(arg0);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		//历史付款单不可编辑
	          	return false;
	}
	
	
	/**
	 * 选择其他级别后更新列表内容
	 */
  public void  setData(ArrayList<Athlete>  athleteList){
  	   
  	    this.data=new Vector<Vector<Object>>();
  	    this.athleteList = athleteList;
  	    for(int i = 0; i< athleteList.size();i++){
			Vector<Object> temp = new Vector<Object> ();
			temp.add( athleteList.get(i).getRank()+"");
			temp.add( athleteList.get(i).getName());
			temp.add( athleteList.get(i).getTeam());
			temp.add(athleteList.get(i).getLevelName());
			temp.add(new JRadioButton("",false));
			data.add(temp);
		}
  	    fireTableDataChanged();
  }
}
