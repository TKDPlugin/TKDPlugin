package bl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LevelStringAnalyze{

    public static void analyzeLevel(Level level) {
        String name = level.getLevelName();
        
        for(Gender gender : Gender.values()) {
            if(name.contains(gender.toChinese())) {
                level.setGender(gender);
            }
        }
        
        String kgRex = "[Kk]g|KG";
        String weightRex = "\\d{2}+";
        String wholeRex = "\\+?"+weightRex + "("+kgRex+")";
        
        String matchString = firstMatch(name,wholeRex);
        if(matchString!=null) {
            KGLevel kgLevel = new KGLevel();
            if(matchString.contains("+")) {
                kgLevel.setGreater(true);
            }
            
            String numString = firstMatch(matchString, weightRex);
            kgLevel.setKg(Integer.parseInt(numString));
            
            level.setKgLevel(kgLevel);
        }
    
    }
    
    public static String firstMatch(String source,String rex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(source);
        if(matcher.find()) {
            return matcher.group();
        }else {
            return null;
        }
    }
    
//    public static void main(String[] args) {
//        Althete althete = new Althete();
//        ArrayList<Althete> arrayList = new ArrayList<>();
//        arrayList.add(althete);
//        
//        String levelName = "俱乐部12kG男子76kg级";
//        Level level = new Level(levelName, arrayList);
//        analyzeLevel(level);
//        System.out.println(level.toString());
//        
//    }

}
