package data.util;



import static data.util.Excutor.cone;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * 启动数据库服务的类
 */
public class DataBaseLancher {
	// 启动数据库，返回数据库引用
	public static Excutor lanch() {
		Config.config();
		Properties prop = new Properties();    //只要添加这几句话就可以  
        prop.put("charSet", "gb2312");      
		
		try {
			// 加载驱动
			Class.forName(Config.driver);
			// 创建连接
			cone = DriverManager.getConnection(Config.url,prop);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Excutor ex = Excutor.getInstance();
		
		return ex;
	}
}
