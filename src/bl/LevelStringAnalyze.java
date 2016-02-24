package bl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bl.Athlete;

public class LevelStringAnalyze {

	public static void analyzeLevel(Level level) {
		String name = level.getLevelName();

		for (Gender gender : Gender.values()) {
			String genderRex = gender.toChinese() + "子?";
			if (firstMatch(name, genderRex) != null) {
				level.setGender(gender);
				name = name.replaceFirst(genderRex, "");
			}
		}

		String kgRex = "[Kk][Gg]";
		String weightRex = "\\d{2}+";
		String wholeRex = "\\+?" + weightRex + "(" + kgRex + ")";

		String matchString = firstMatch(name, wholeRex);
		if (matchString != null) {
			KGLevel kgLevel = new KGLevel();
			if (matchString.contains("+")) {
				kgLevel.setGreater(true);
			}

			String numString = firstMatch(matchString, weightRex);
			kgLevel.setKg(Integer.parseInt(numString));
			level.setKgLevel(kgLevel);

			name = name.replaceFirst(wholeRex, "");
		}
		level.setGroupString(name);
	}

	public static String firstMatch(String source, String rex) {

		Pattern pattern = Pattern.compile(rex);
		Matcher matcher = pattern.matcher(source);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return null;
		}

	}

//	public static void main(String[] args) {
//		
//		Athlete athlete = new Athlete();
//		ArrayList<Athlete> arrayList = new ArrayList<>();
//		arrayList.add(athlete);
//
//		//String levelName = "男俱乐部12KG";
//		String levelName = "男子俱乐部12KG";
//		Level level = new Level(levelName, arrayList);
//		//analyzeLevel(level);
//		System.out.println(level.toString());
//		System.out.println(level.getLevelName());
//		System.out.println(level.getGroupString());
//	}

}
