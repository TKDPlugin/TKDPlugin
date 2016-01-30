package bl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.DataController;
import data.util.ListMaker;
import data.util.SQLBuilder;

public class BlController {
    private static BlController controller;
    private DataController dataController;
  //  ArrayList<Level> levelList;
    private BlController() {
        dataController = DataController.getInstance();
    }
    
    public static BlController getInstance() {
        if(controller!=null) {
            controller = new BlController();
        }
        return controller;
    }
    
	public ArrayList<Level> getSelectedLevels() {
		return dataController.getSelectedLevels();
	}

	/**
	 * @return boolean 是否比赛完全结束，所有人都有名次
	 * 
	 */
	public boolean isAllFinish() {
		return dataController.isAllFinish();
	}

    
}
