package data.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 发现很多创建POlist的代码是重复的，执行一条SQL语句->创建一个ArrayList对象->循环，每次创建出一个对象，加入list->结束<br/>
 * 为了解决重复<br/>
 * 这是一个模板方法，使用方法是，继承他，实现他的getPO方法。然后new一个对象出来<br/>
 * 然后调用他的getList，可以传入表名，或者一条sql语句，或者一个SQLBuilder，然后接收List就行<br/>
 * 传SQL语句是为了适应某些需要加判断语句的情况。<br/>
 * 不过也允许在自己实现的getPO方法里写判断，假如条件不符合就返回null，list不会接收null的元素
 * 在ListMakerTest中有实例代码，可以看一下
 * @author 申彬
 *
 * @param <T>
 */
public abstract class ListMaker<T> {

	private ResultSet resultSet;

	public abstract T getPO(ResultSet resultSet);

	public final ArrayList<T> getList(SQLBuilder builder) {
		return getList(builder.getSQL());
	}

	/**
	 * 若没找到返回一个空的Arraylist
	 * @param SQL_or_TableName
	 * @return
	 */
	public final ArrayList<T> getList(String SQL_or_TableName) {
		Excutor excutor = Excutor.getInstance();
		String sql = null;
		// 若传入的string中不包含select，则视为表名，转化为SQL语句
		if (!SQL_or_TableName.toLowerCase().contains("select")) {
			sql = new SQLBuilder().Select("*").From(SQL_or_TableName).getSQL();
		} else {
			sql = SQL_or_TableName;
		}

		ArrayList<T> list = new ArrayList<>();
		try {
			resultSet = excutor.excuteQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(resultSet==null){
//			return new ArrayList<>();
//		}

		try {
			while (resultSet.next()) {
				T po = getPO(resultSet);
				if (po != null) {
					list.add(po);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
