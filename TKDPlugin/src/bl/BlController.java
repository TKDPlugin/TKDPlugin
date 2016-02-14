package bl;

import java.util.ArrayList;
import java.util.HashMap;

import data.DataController;
import data.util.DataBaseLancher;

public class BlController {
	private static BlController controller;
	private DataController dataController;

	// ArrayList<Level> levelList;
	private BlController() {
		dataController = DataController.getInstance();
	}

	public static BlController getInstance() {

		if (!DataBaseLancher.isLanched()) {
			DataBaseLancher.lanch();
		}

		if (controller == null) {
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
	
	public ArrayList<Group> getAllGroup(){
		assert isAllFinish() : "Completion isn't finished";
		
		ArrayList<Level> levels = getSelectedLevels();
		HashMap<String, ArrayList<Level>> groupMap = new HashMap<>();
		
		for(Level aLevel:levels){
			String groupName = aLevel.getGroupString();
			//还没有这个组名
			if(!groupMap.containsKey(groupName)){
				groupMap.put(groupName, new ArrayList<Level>());
			}
			
			groupMap.get(groupName).add(aLevel);
		}
		
		ArrayList<Group> groups = new ArrayList<>(groupMap.keySet().size());
		
		for(String name:groupMap.keySet()){
			ArrayList<Level> groupLevel = groupMap.get(name);
			ArrayList<Level> maleLevel = new ArrayList<>();
			ArrayList<Level> femaleLevel = new ArrayList<>();
			
			//分拣男女list
			for(Level level:groupLevel){
				switch(level.getGender()){
				case male:
					maleLevel.add(level);
					break;
				case female:
					femaleLevel.add(level);
					break;
				}
			}
			
			groups.add(new Group(maleLevel, femaleLevel, name));
		}
		
		return groups;
	}
	
	public boolean isAllFinish() {
		return dataController.isAllFinish();
	}

}
