package ui.export;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class TextReplace {
	
	String name = "name";
	String level = "level";
	String rank = "rank";
	String dirName;
	
	
	private TextReplace(){}
	
	public void replace(MyDoc myDoc,HashMap<String,String> map){
		List<XWPFParagraph> paragraphList = myDoc.getParagraphs();
		
		for(XWPFParagraph paragraph:paragraphList) {
			List<XWPFRun> runs = paragraph.getRuns();
			//System.out.println(paragraph.getText());
			for(XWPFRun run:runs){
				String text = run.getText(0);
			//	System.out.println(text);
				if(text==null){
					continue;
				}
				
				for(String key:map.keySet()){
					text = text.replace(key, map.get(key));
				}
//				text = text.replaceAll(name, "王磊");
//				text = text.replaceAll(level, "男甲54Kg");
//				text = text.replaceAll(rank, "第 10 名");	
				run.setText(text, 0);
			}		

		}
		
	}
	
	public static void exportWord(List<String> names,List<String> levels,
			List<String> ranks){
		TextReplace replace = new TextReplace();
		replace.dirName = levels.get(0);
		
		File file = new File(replace.dirName);
		if(!file.isDirectory()){
			file.mkdir();
		}
		
		for(int i=0;i<ranks.size();i++){
			String rank = ranks.get(i);
			rank = "第 "+rank+" 名";
			ranks.set(i, rank);
		}
		
		for(int i=0;i<names.size();i++){
			MyDoc myDoc = POIUtility.openDoc("获奖证书模板.docx");
			HashMap<String, String> map = new HashMap<>();
			map.put(replace.name, names.get(i));
			map.put(replace.level, levels.get(i));
			map.put(replace.rank, ranks.get(i));
			
//			for(String key:map.keySet()){
//				System.out.println(key+":"+map.get(key));
//			}
			
			replace.replace(myDoc,map);
			POIUtility.writeDoc(myDoc, replace.dirName+"/"+names.get(i)+"获奖证书.docx");
		}
		
	}
	
	
	public static void main(String[] args) {
		List<String> names = Arrays.asList("李雷","韩梅梅","小明");
		List<String> levels = Arrays.asList("男甲45Kg","男甲45Kg","男甲45Kg");
		List<String> ranks = Arrays.asList("1","2","3");
		TextReplace.exportWord(names, levels, ranks);
	}
}
