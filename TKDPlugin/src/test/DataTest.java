package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import bl.Level;
import data.DataController;
import data.util.DataBaseLancher;

public class DataTest {
	DataController datacontroller;
	@Before
	public void setUp() throws Exception {
		DataBaseLancher.lanch();
		datacontroller = DataController.getInstance();
	}



	@Test
	public void testGetSelectedLevels() {
		ArrayList<Level> levels = datacontroller.getSelectedLevels();
		System.out.println(ObjToString.toString(levels));
	}
	
	@Test
	public void getLevel(){
		String levelName = "男甲45kg";
		Level level = datacontroller.getALevel(levelName);
		System.out.println(ObjToString.toString(level));
	}

	@Test
	public void testIsAllFinish() {
		System.out.println("是否结束："+datacontroller.isAllFinish());
	}

}
