package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bl.Althete;
import bl.Level;
import data.util.DataBaseLancher;
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

    public ArrayList<Level> getSelectedLevel() {
        ArrayList<String> selectedName = new ArrayList<>();
        SQLBuilder builder = new SQLBuilder();
        builder.Select(levelNameCol).From(levelTableName)
                .Where(levelSelectedCol + " = true");
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

        for (String levelName : selectedName) {

        }

        return null;
    }

    private Level getALevel(String levelName) {
        ArrayList<Althete> altheteList = new ArrayList<>();
        SQLBuilder builder = new SQLBuilder();
        builder.Select("*").From(altTableName).Where("AthTeam")
                .EQUALS(levelName).AND("LastPlace > 0");
        ResultSet set = builder.excuteQuery();
        try {
            while (set.next()) {
                String name;
                int rank;
                String team;
               // String levelName;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @return boolean 是否比赛完全结束，所有人都有名次
     * 
     */
    public boolean isAllFinish() {
        SQLBuilder builder = new SQLBuilder();
        builder.Select("count(*)").From(altTableName)
                .Where(placeCol + " IS NULL").OR(placeCol).EQUALS(0);
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
