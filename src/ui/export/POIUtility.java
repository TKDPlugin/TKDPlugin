package ui.export;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

public class POIUtility {

	public static MyDoc openDoc(String path) {
		MyDoc doc = null;
		try {
			doc = new MyDoc(new FileInputStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void writeDoc(XWPFDocument doc,String path){
		FileOutputStream out;
		try {
			out = new FileOutputStream(path);
			doc.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static XWPFTable copyTable(XWPFTable table) {
		// Copying a existing table
		// Create a new CTTbl for the new table
		CTTbl ctTbl = CTTbl.Factory.newInstance();
		// Copy the template table's CTTbl
		ctTbl.set(table.getCTTbl());
		// Create a new table using the CTTbl upon
		XWPFTable newTable = new XWPFTable(ctTbl, table.getBody());		
		return newTable;
		// doc.createParagraph();
		// doc.createTable(); // Create a empty table in the document
		// doc.setTable(1, table2); // Replace the empty table to table2
	}
}
