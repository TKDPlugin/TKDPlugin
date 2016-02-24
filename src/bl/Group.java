package bl;

import java.util.ArrayList;
import java.util.Collections;

public class Group {
	private ArrayList<Level> maleLevels;
	private ArrayList<Level> femaleLevels;
	private String groupName;
	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public Group(ArrayList<Level> maleLevels,ArrayList<Level> femaleLevels,String groupName){
		this.maleLevels = maleLevels;
		this.femaleLevels = femaleLevels;
		this.groupName = groupName;
	}
	
	
	public ArrayList<Level> getLevels(Gender gender) {
		
		switch (gender) {
		case male:
			return maleLevels;
			
		case female:
			return femaleLevels;
		}
		return null;
	}
	
	public int getLevelNum(Gender gender){
		if(gender == null){
			return maleLevels.size()+femaleLevels.size();
		}
		else {
			return getLevels(gender).size();
		}	
	}

	public ArrayList<Level> getAllLevel(){
		ArrayList<Level> allLevel = new ArrayList<Level>(maleLevels);
		allLevel.addAll(femaleLevels);
		return allLevel;
	}
	
}
