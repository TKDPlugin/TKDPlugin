package bl;

import java.util.ArrayList;
import java.util.Collections;

import javax.naming.spi.DirStateFactory.Result;

public class Level implements Comparable<Level>{
    private ArrayList<Athlete> altheteList;
    private String levelName;
    private Gender gender;
    private KGLevel kgLevel;
  
    public Level(String levelName,ArrayList<Athlete> list) {
        this.levelName = levelName;
        LevelStringAnalyze.analyzeLevel(this);
        altheteList = new ArrayList<>(list);
        Collections.sort(altheteList);
    }
    
    public String getLevelName() {
        return levelName;
    }



    public ArrayList<Athlete> getAltheteList() {
        return altheteList;
    }



    public void setAltheteList(ArrayList<Athlete> altheteList) {
        this.altheteList = altheteList;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

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
  
}
