package test;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import com.hxtt.sql.bl;

import bl.BlController;

public class BLTest {

	BlController bl;
	@Before
	public void setUp() throws Exception {
		bl = BlController.getInstance();
	}

	@Test
	public void testGetSelectedLevels() {
		System.out.println(bl == null);
		System.out.println(ObjToString.toString(bl.getSelectedLevels()));
	}

	@Test
	public void testIsAllFinish() {
		System.out.println("Is all Finished :"+ObjToString.toString(bl.isAllFinish()));
	}
	
	@Test
	public void testGetGroup(){
		System.out.println(ObjToString.toString(bl.getAllGroup()));
	}

	@Test
	public void testGetMostRank(){
		int[] rank = bl.getMostRank(bl.getAllGroup());
		System.out.println(ObjToString.toString(rank));
	}
}
