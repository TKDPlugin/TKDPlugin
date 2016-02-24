package data.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase {
	public static void main(String[] args) throws IOException {

		try {
			Excutor excutor = DataBaseLancher.lanch();
			
			String testSQL = "select * from tabAth";
			ResultSet re = excutor.excuteQuery(testSQL);
			while(re.next()){
				System.out.println(re.getString(1));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
