package ui.export;
/*
 * 排名表格使用该类进行简单的导出。
 */

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExportExcelUtil {
	
	public static String  getPath(){
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		
		FileDialog fileDialog = new FileDialog(new JFrame(), null, FileDialog.SAVE);
		fileDialog.setLocation(width-fileDialog.getWidth()/2, height-fileDialog.getHeight()/2 );
		fileDialog .setVisible(true);
		String path = fileDialog.getDirectory();
		String fileName = fileDialog.getFile()+".xls";
		System.out.println(path+fileName);
		return path+fileName;
	}

}
