package ui.export;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import org.apache.poi.util.PackageHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFactory;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class MyDoc extends XWPFDocument{
	
    public MyDoc(InputStream is) throws IOException {
        super(is);
    }

    public MyDoc() {
    	super();
    }
	
	
	public void appendTable(XWPFTable table){
		createParagraph();
		createTable(); // Create a empty table in the document
		setTable(getTables().size()-1, table);  // Replace the empty table to table2
	}
}
