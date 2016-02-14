package bl;

import java.util.ArrayList;
import java.util.Collections;

import javax.naming.spi.DirStateFactory.Result;

public class Level implements Comparable<Level>{
    private ArrayList<Athlete> altheteList;
    private String levelName;
    private Gender gender;
    private KGLevel kgLevel;
    private String groupString;
    private int[] places;
    
    public int[] getPlaces() {
		return places;
	}

	public void setPlaces(int[] places) {
		this.places = places;
	}

	public Level(String levelName,ArrayList<Athlete> list) {
        this.levelName = levelName;
        LevelStringAnalyze.analyzeLevel(this);
        altheteList = new ArrayList<>(list);
        Collections.sort(altheteList);
        
        places = new int[altheteList.size()];
        for(int i=0;i<altheteList.size();i++){
        	Athlete athlete = altheteList.get(i);
        	places[i] = athlete.getRank();
        }
    }
    
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

    public ArrayList<Athlete> getAltheteList() {
        return altheteList;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * kgLevel的toString可以得到 45Kg或者+45Kg这种字符串
     */
    public KGLevel getKgLevel() {
        return kgLevel;
    }

    public void setKgLevel(KGLevel kgLevel) {
        this.kgLevel = kgLevel;
    }
    
    public String toString() {
        //String result = gender.toChinese()+"子";
        //return result+kgLevel.toString();
    	return levelName;
    }

    @Override
    public int compareTo(Level o) {
        
        int result=0;
        if((result = kgLevel.compareTo(o.kgLevel))!=0) {
            return result;
        }else {
            return gender.compareTo(o.gender);
        }
    }

	public String getGroupString() {
		return groupString;
	}

	public void setGroupString(String groupString) {
		this.groupString = groupString;
	}
  
}
