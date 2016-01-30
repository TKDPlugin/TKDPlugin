package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bl.Althete;
import bl.Level;
import data.util.DataBaseLancher;
import data.util.ListMaker;
import data.util.SQLBuilder;

public class DataController {
	private static DataController dataController;

	private String altTableName = "tabAth";

	private String placeCol = "LastPlace";

	private String levelTableName = "tabClass";

	private String levelSelectedCol = "Selected";

	private String levelNameCol = "ClassItem";

	private DataController() {
		DataBaseLancher.lanch();
	};

	public static DataController getInstance() {
		if (dataController == null) {
			dataController = new DataController();
		}
		return dataController;
	}

	public ArrayList<Level> getSelectedLevels() {
		ArrayList<String> selectedName = new ArrayList<>();
		SQLBuilder builder = new SQLBuilder();
		builder.Select(levelNameCol).From(levelTableName).Where(levelSelectedCol + " = true");
		ResultSet set = builder.excuteQuery();
		try {
			while (set.next()) {
				selectedName.add(set.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		ArrayList<Level> levels = new ArrayList<>();
		for (String levelName : selectedName) {
			levels.add(getALevel(levelName));
		}

		return levels;
	}

	public Level getALevel(String levelName) {
		SQLBuilder builder = new SQLBuilder();
		builder.Select("*").From(altTableName).Where("AthClass")
		.EQUALS(levelName).AND("LastPlace > 0");

		ListMaker<Althete> altheteMaker = new ListMaker<Althete>() {
			@Override
			public Althete getPO(ResultSet set) {
				try {
					String name = set.getString("AthName");
					int rank = set.getInt("LastPlace");
					String team = set.getString("AthTeam");
					Althete althete = new Althete();
					althete.setLevelName(levelName);
					althete.setName(name);
					althete.setRank(rank);
					althete.setTeam(team);
					return althete;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}

		};

		ArrayList<Althete> althetes = altheteMaker.getList(builder);
		Level level = new Level(levelName, althetes);
		return level;
	}

	/**
	 * @return boolean 是否比赛完全结束，所有人都有名次
	 * 
	 */
	public boolean isAllFinish() {
		SQLBuilder builder = new SQLBuilder();
		builder.Select("count(*)").From(altTableName).Where(placeCol + " IS NULL").OR(placeCol).EQUALS(0);
		ResultSet set = builder.excuteQuery();
		try {
			if (set.next()) {
				int count = set.getInt(1);
				if (count == 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
	}

}
