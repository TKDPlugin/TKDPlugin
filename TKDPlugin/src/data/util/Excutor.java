package data.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 执行SQL语句对数据库进行处理的类
 */
public class Excutor {
	protected static Connection cone;
	private static Excutor excutor;

	public static Excutor getInstance() {
		if (excutor == null)
			excutor = new Excutor();
		return excutor;
	}

	private Excutor() {
	}

	/**
	 * 执行查询语句
	 * @param sql 具体查询的sql语句
	 * @return 查询的结果集
	 * @throws SQLException 
	 */
	public ResultSet excuteQuery(String sql) throws SQLException {
		if (cone == null)
			throw new NullPointerException("数据库未连接");
		Statement statement = cone.createStatement();
		return statement.executeQuery(sql);

	}

	/**
	 * 执行语句
	 * @param sql 具体的sql语句
	 * @return 查询的结果集
	 * @throws SQLException 
	 */
	public boolean excute(String sql) throws SQLException {
		if (cone == null)
			throw new NullPointerException("数据库未连接");
		Statement statement = cone.createStatement();
		return statement.execute(sql);

	}

	public void close() throws SQLException {
		cone.close();
	}
}
