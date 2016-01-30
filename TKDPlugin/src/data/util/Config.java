package data.util;

/**
 * 
 * @author YangYanfei
 * 存贮数据库配置信息的类
 */
public class Config {
	/**
	 * 数据库驱动、地址、用户名、密码
	 */
	public static String url,driver;
	public static String filePath = "server/config.txt";

	/**
	 * 读取配置信息
	 */
	public static void config() {

		driver = "com.hxtt.sql.access.AccessDriver";
		url = "jdbc:Access:///Athlete.mdb";
	}
}
