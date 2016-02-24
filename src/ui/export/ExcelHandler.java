package ui.export;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import bl.Gender;
import bl.Group;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelHandler {
      private OutputStream reportOS;
    
    //表头的字体格式，字体、大小和样式 
      private final static WritableFont HEADER_FONT_STYLE = new WritableFont(     
    		  WritableFont.TIMES, 12, WritableFont.BOLD);    
      //内容的字体格式     
      private final static WritableCellFormat BODY_FONT_STYLE = new WritableCellFormat(  
    		  new WritableFont(WritableFont.TIMES,     WritableFont.DEFAULT_POINT_SIZE));    
   /**
    * 构造函数需要导出路径作为参数
    * @param filePath
    */
      public ExcelHandler(String filePath ) {      
    	  try { 
  
                   File file = new File(filePath); 
                   if (!file.exists()) {              
                	  file.createNewFile();         
                   }              
                 
                    this.reportOS = new FileOutputStream(filePath);   
                 
                 } catch (Exception e) {    
                	 System.out.println("ExcelHandler 启动失败");
                 }  
    	  }
      
      public void exportRankExcel(JTable table){
    	  try{
    		  
    		  TableModel model = table.getModel();
    		  
    		  WritableWorkbook workBook =
    				  Workbook.createWorkbook(this.reportOS);
    		
    		  WritableSheet sheet = workBook.createSheet("跆拳道排名表", 0);
    		  
    		  
    		//创建表头的单元格格式         
    		  WritableCellFormat headerFormat = new WritableCellFormat(     
    				  HEADER_FONT_STYLE);         
    		  //水平方向居中对齐
    		  headerFormat.setAlignment(Alignment.CENTRE);
    		  //竖直方向居中对齐           
    		  headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
    		  for(int i=0;i<model.getColumnCount();i++){
    			    sheet.addCell(new Label(i,0,model.getColumnName(i),headerFormat));
    		  }
    		  
    		//创建内容的单元格格式         
    		  WritableCellFormat bodyFormat = new WritableCellFormat(     
    				  BODY_FONT_STYLE);         
    		  //水平方向居中对齐
    		  bodyFormat.setAlignment(Alignment.CENTRE);
    		  //竖直方向居中对齐           
    		  bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
    		  for(int i=0;i<model.getRowCount();i++){
    			    for(int j=0;j<model.getColumnCount();j++){
    			    	sheet.addCell(new Label(j,i+1,model.getValueAt(i, j).toString(),bodyFormat));
    			    }
    		  }

    	      workBook.write();
    	      workBook.close();
    	      reportOS.close();
    	  
    	  }catch (Exception e){
    		  e.printStackTrace();
    	  }
      }
      
      
      public void  exportSumExcel(int [] ranks, ArrayList<Group>  groups){
    	  
    	  try{
    		  System.out.println("共有"+groups.size()+"组");
    		  
    		  WritableWorkbook workBook =
    				  Workbook.createWorkbook(this.reportOS);
    		
    		  WritableSheet sheet = workBook.createSheet("跆拳道汇总表1", 0);

    	      this.writeHeader(sheet,ranks);
    	      this.writeBody(sheet,groups);
    	      
    	      workBook.write();
    	      workBook.close();
    	      reportOS.close();
    	  
    	  }catch (Exception e){
    		  e.printStackTrace();
    	  }
      }
      
      private void writeHeader(WritableSheet sheet ,int [] ranks){
    	  try {             
    		  
    		  //创建表头的单元格格式         
    		  WritableCellFormat headerFormat = new WritableCellFormat(     
    				  HEADER_FONT_STYLE);         
    		  //水平方向居中对齐
    		  headerFormat.setAlignment(Alignment.CENTRE);
    		  //竖直方向居中对齐           
    		  headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
    		  //坐标（列,行）
    		  Label LB_0 = new Label (0,0,"                     成绩名次",headerFormat);
    		  sheet.addCell(LB_0);
    		  
    		  Label LB_1 = new Label (0,1,"组别项目",headerFormat);
    		  sheet.addCell(LB_1);
    		  
    		//合并单元格，参数依次为：列索引、行索引、列索引+需要合并的列的个数、行索引+需要合并的行的个数 
    		  sheet.mergeCells(0, 0, 2, 0);
    		  sheet.mergeCells(0, 1, 2, 1);
    		  
    		  for(int i=0;i<ranks.length;i++){
    		      sheet .addCell(new Label (3+i*2,0,"第"+ranks[i]+"名",headerFormat));
    		      sheet.mergeCells(3+i*2, 0, 3+i*2+1,0 );
    		  }
    		  
    		  
    		  for(int i=3;i<3+2*ranks.length;i++){
    			  if(i%2!=0){
    			    sheet.addCell(new Label(i,1,"姓名",headerFormat));
    			  }else{
    				sheet.addCell(new Label(i,1,"单位",headerFormat));
    			  }
    			
    		  }
    		  
    		  
    		  
    	  }catch (Exception e){
    		  e.printStackTrace();
    	  }
      }
      
      
      
      private void writeBody(WritableSheet sheet,ArrayList<Group>  groups){
    	  try{
    		//创建内容的单元格格式         
    		  WritableCellFormat bodyFormat = new WritableCellFormat(     
    				  BODY_FONT_STYLE);         
    		  //水平方向居中对齐
    		  bodyFormat.setAlignment(Alignment.CENTRE);
    		  //竖直方向居中对齐           
    		  bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
    		  
    		//组名的起始行数
    		  int [] Index_GroupNames = new int [groups.size()];
    		  
    		  for(int i=0;i<groups.size();i++){
    			    if(i==0){
    			    	Index_GroupNames[i]=2;
    			    }else{
    			    	Index_GroupNames[i]=Index_GroupNames[i-1]+groups.get(i-1).getLevelNum(null);
    			    }
    		  }
    		  
    		  
    		  for(int i=0;i<groups.size();i++){
    			  //设置组名
    			  sheet.addCell(
    					  new Label(0, Index_GroupNames[i], 
    							  groups.get(i).getGroupName().toString(),bodyFormat));
    			  
    			  sheet.mergeCells(0,Index_GroupNames[i] ,
    					  0,Index_GroupNames[i]+ groups.get(i).getLevelNum(null)-1);
    			  
    			  //设置每组的男子
    			  sheet.addCell(
    					  new Label(1, Index_GroupNames[i], 
    							  "男子",bodyFormat));
    			  sheet.mergeCells(1,Index_GroupNames[i],
    					  1, Index_GroupNames[i]+ groups.get(i).getLevelNum(Gender.male)-1);
    			  
    			  for(int j=0;  j<groups.get(i).getLevelNum(Gender.male);j++){
    				  //男甲45kg
    				  sheet.addCell(
        					  new Label(2, Index_GroupNames[i]+j, 
        							 groups.get(i).getLevels(Gender.male).get(j).getLevelName().toString(),bodyFormat));
    				//一到八名、十二名、十六名。。
    				  for(int k=0;k<groups.get(i).getLevels(Gender.male).get(j).getAltheteList().size();k++){
    			          sheet.addCell(
            					  new Label(3+k*2, Index_GroupNames[i]+j, 
            							 groups.get(i).getLevels(Gender.male).get(j).getAltheteList().get(k).getName(),bodyFormat));
    			      
    			          sheet.addCell(
        					  new Label(3+k*2+1, Index_GroupNames[i]+j, 
        							 groups.get(i).getLevels(Gender.male).get(j).getAltheteList().get(k).getTeam(),bodyFormat));
			           }
    		}
    				  
    				  

    			  
    			  
    			  
    			//设置每组的女子
    			  int Index_female = Index_GroupNames[i]+ groups.get(i).getLevelNum(Gender.male);
    			 
    			  sheet.addCell(
    					  new Label(1, Index_female, 
    							  "女子",bodyFormat));
    			  sheet.mergeCells(1,Index_female,
    					  1, Index_female+ groups.get(i).getLevelNum(Gender.female)-1);
    			  
    			  for(int j=0;  j<groups.get(i).getLevelNum(Gender.female);j++){
    				  //女甲50kg
    				  sheet.addCell(
        					  new Label(2, Index_female+j, 
        							 groups.get(i).getLevels(Gender.female).get(j).getLevelName().toString(),bodyFormat));
    				//一到八名、十二名、十六名。。
    				  for(int k=0;k<groups.get(i).getLevels(Gender.female).get(j).getAltheteList().size();k++){
    			          sheet.addCell(
            					  new Label(3+k*2, Index_female+j, 
            							 groups.get(i).getLevels(Gender.female).get(j).getAltheteList().get(k).getName(),bodyFormat));
    			      
    			          sheet.addCell(
        					  new Label(3+k*2+1, Index_female+j, 
        							 groups.get(i).getLevels(Gender.female).get(j).getAltheteList().get(k).getTeam(),bodyFormat));
			           }
    			 }
    			  
    		  }
    		  
    	  }catch(Exception e){
              e.printStackTrace();     
    	  }
      }
      
      
}
