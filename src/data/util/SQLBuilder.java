package data.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import data.util.Excutor;

/**
 *
 * 一个SQL生成器，有执行方法，用法示例如下:<p/>
 *
 *	SQLBuilder sqlBuilder = new SQLBuilder();	<br/>
 *	sqlBuilder.Select("Name","Balance","Income").From("account").Where("Balance > 10000");<br/>
 *	sqlBuilder.excuteQuery();<p/>
 *
 *	这个SQL生成器大部分时候允许两种输入方法，当想输入一串列或表达式时，可以通过方法一个一个调用输入，如<br/>
 *	Set("Name").Assign("账户1").Comma("balance").Assign(12321.5)<p/>
 *	也可以直接输入一个字符串
 *	如<br/>
 *	(Balance = 100,name = '账户1')<p/>
 *
 *	excute和excuteQuery执行完了之后会自动清空builder<p/>
 *	想把日期格式变成datetime，就调用setType方法。
 * @author 申彬
 *
 */
public class SQLBuilder {

//	public static void main(String[] args) {
//		SQLBuilder sqlBuilder = new SQLBuilder();
//		// sqlBuilder.Select("T.account","T.balance").From("account").AS("T")
//		// .Where("Balance").EQUALS(150).AND("Income").EQUALS(200);
//		// System.out.println(sqlBuilder.getSQL());
//		//
//		// sqlBuilder.clear();
//		// sqlBuilder.InsertInto("account",
//		// "(balance,name,income)").Values("(15,100,'test')");
//		// System.out.println(sqlBuilder.getSQL());
//		//
//		sqlBuilder.clear();
//		sqlBuilder.InsertInto("account").Values(15, 100, "test");
//		System.out.println(sqlBuilder.getSQL());
//		//
//		sqlBuilder.clear();
//		sqlBuilder.Update("account").Set("Name").Assign("账户1").Comma("balance")
//				.Assign(12321.5).Where("Income").EQUALS(12345);
//		System.out.println(sqlBuilder.getSQL());
//		//
//		// sqlBuilder.clear();
//		// sqlBuilder.Update("account").Set("Name = '账户1'").Comma("balance = 12233")
//		// .Where("Income = 12345");
//		// System.out.println(sqlBuilder.toString());
//		//
//		//
//		sqlBuilder.clear();
//		// sqlBuilder.DeleteFrom("account").Where("Name").EQUALS("账户1");
//		// System.out.println(sqlBuilder.getSQL());
//		sqlBuilder.handleString("2015-11-01");
//		System.out.println(sqlBuilder.getSQL());
//		sqlBuilder.clear();
//		sqlBuilder.handleString("2015-11-01 22:15:1");
//		System.out.println(sqlBuilder.getSQL());
//		sqlBuilder.clear();
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2015, 11, 11);
//
//		sqlBuilder.Select("*").From("stockoutorder").Where("ins_id")
//				.EQUALS("00110").AND("time").Between(calendar).AND(calendar);
//
//		System.out.println(sqlBuilder.toString());
//		sqlBuilder.clear();
//
//		sqlBuilder.InsertInto(PaymentListTable.tableName,
//				PaymentListTable.paymentListID_col, PaymentListTable.total_col,
//				PaymentListTable.payer_col, PaymentListTable.date_col,
//				PaymentListTable.passed_col).Values("11111", 50.3, "小春",
//				Calendar.getInstance(), 0);
//		System.out.println(sqlBuilder.toString());
//
//		sqlBuilder.clear();
//		sqlBuilder.Select("*").From("billorder").Where("time")
//				.EQUALS("2015-11-26").AND("order_id").LIKE("0010001%");
//		System.out.println(sqlBuilder);
//		sqlBuilder.clear();
//
//	}

	private Excutor excutor;
	private StringBuilder builder;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-mm-dd hh:mm:ss");
	private DateType dateType = DateType.date;

	public SQLBuilder() {
		excutor = Excutor.getInstance();
		builder = new StringBuilder();
	}

	public enum DateType {
		date, datetime
	}

	public void setType(DateType type) {
		this.dateType = type;
	}

	public boolean equals() {
		System.out.println("想输入等号，请不要调用equals，请调用EQUALS");
		return false;
	}

	public boolean excute() {

		String sql = builder.toString();
		clear();
		try {
			excutor.excute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL语句有问题");
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}

	}

	public ResultSet excuteQuery() {
		ResultSet resultSet = null;
		String sql = builder.toString();
		clear();
		try {
			resultSet = excutor.excuteQuery(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQL语句有问题");
			System.out.println(sql);
			e.printStackTrace();
		}

		return resultSet;
	}

	public String getSQL() {
		return builder.toString();
	}

	public SQLBuilder InsertInto(String table, String... column) {
		builder.append("Insert into ");
		builder.append(table);
		builder.append(" ");
		if (column.length > 0) {
			leftBra();
		}
		makeList(column, true);
		return this;
	}

	public SQLBuilder Values(Object... values) {
		deleteSpace();
		if (builder.toString().contains("(")) {
			rightBra();
		}
		builder.append(" Values ");
		if (values.length == 0) {
			System.out.println("值列表为空");
		}
		makeList(values, false);
		return this;

	}

	/**
	 *
	 * 删除方法，假如后边不调用where，则删除表中所有行
	 * @param table
	 * @return
	 */
	public SQLBuilder DeleteFrom(String table) {
		builder.append("Delete From ");
		builder.append(table);
		builder.append(" ");
		return this;
	}

	public SQLBuilder Update(String table) {
		builder.append("Update ");
		builder.append(table);
		builder.append(" ");
		return this;
	}

	public SQLBuilder Set(String col_or_express) {
		builder.append("Set ");
		builder.append(col_or_express);
		return this;
	}

	public SQLBuilder Assign(int num) {
		EQUALS(num);
		return this;
	}

	public SQLBuilder Assign(double num) {
		EQUALS(num);
		return this;
	}

	public SQLBuilder Assign(String value) {
		EQUALS(value);
		return this;
	}

	public SQLBuilder Assign(Enum e) {
		return Assign(e.toString());
	}

	/**
	 * 用于在update中加","的语句，传入参数是列名
	 * @param column
	 * @return
	 */
	public SQLBuilder Comma(String col_or_express) {

		// 为了让生成的SQL语句好看一点，逗号语句删除原字符串末尾的空格
		deleteSpace();
		builder.append(",");
		builder.append(col_or_express);

		return this;
	}

	private void makeList(Object[] objects, boolean isColumnName) {
		if (objects.length == 0) {
			return;
		} else {
			boolean hasBra = false;
			if (objects[0] instanceof String) {
				String s = (String) objects[0];
				hasBra = s.contains("(");
			}

			if (!hasBra && !isColumnName) {
				leftBra();
			}

			for (int i = 0; i < objects.length; i++) {
				if (i > 0) {
					builder.append(",");
				}
				// 在不是列名的情况下，string是特殊情况，需要加单引号。
				// 同时也允许用户直接传(150,20,'aa')这样的形式进来，所以假如没有括号才特殊处理
				if (!isColumnName && (objects[i] instanceof String) && !hasBra) {
					String value = objects[i].toString();
					handleString(value);
				} else if (objects[i] instanceof Calendar) {
					String time = convertDate((Calendar) objects[i], dateType);
					handleString(time);
				} else if (objects[i] instanceof Enum) {
					handleEnum((Enum) objects[i]);
				} else {
					builder.append(objects[i].toString());
				}
			}

			if (!hasBra && !isColumnName) {
				rightBra();
			}
			builder.append(" ");
		}
	}

	private void handleEnum(Enum e) {
		handleString(e.toString());
	}

	public SQLBuilder Select(String... column) {
		builder.append("select ");

		if (column.length == 0) {
			return this;
		}

		makeList(column, true);
		return this;
	}

	public SQLBuilder From(String tableName) {
		builder.append("from ");
		builder.append(tableName);
		builder.append(" ");
		return this;
	}

	public SQLBuilder leftBra() {
		builder.append("(");
		return this;
	}

	public SQLBuilder rightBra() {
		builder.append(")");
		return this;
	}

	public SQLBuilder AS(String alias) {
		builder.append("AS " + alias + " ");
		return this;

	}

	private void addSpace() {
		int lastIndex = builder.length() - 1;
		if (builder.charAt(lastIndex) != ' ') {
			builder.append(" ");
		}

	}

	private void deleteSpace() {
		int lastIndex = builder.length() - 1;
		if (builder.charAt(lastIndex) == ' ') {
			builder.deleteCharAt(lastIndex);
		}
	}

	/**
	 *
	 * 若传入列名，记得调用 EQUALS，或者 AND，或者OR，EQUALS相当于 = <br/>
	 * 直接传入表达式也行
	 * @param col_Or_express
	 * @return
	 */
	public SQLBuilder Where(String col_Or_express) {
		addSpace();
		builder.append("Where ");
		builder.append(col_Or_express);

		return this;
	}

	public SQLBuilder EQUALS(int num) {
		judgeKeyWord("=");
		builder.append(num + " ");
		return this;
	}

	public SQLBuilder EQUALS(double num) {
		judgeKeyWord("=");
		builder.append(num + " ");
		return this;
	}

	public SQLBuilder EQUALS(String string) {
		judgeKeyWord("=");
		handleString(string);
		builder.append(" ");
		return this;
	}

	public SQLBuilder EQUALS(Calendar calendar, DateType type) {
		String time = convertDate(calendar, type);
		EQUALS(time);
		return this;
	}

	public SQLBuilder EQUALS(Enum e) {
		EQUALS(e.toString());
		return this;
	}

	public SQLBuilder EQUALS(Calendar calendar) {
		EQUALS(calendar, DateType.date);
		return this;
	}

	public SQLBuilder LIKE(String string) {
		addSpace();
		builder.append("LIKE ");
		handleString(string);
		return this;
	}

	private String convertDate(Calendar calendar, DateType type) {
		String time = null;
		if (type == DateType.date) {
			time = dateFormat.format(calendar.getTime());
		} else if (type == DateType.datetime) {
			time = dateTimeFormat.format(calendar.getTime());
		}
		return time;
	}

	public SQLBuilder Between(int num) {
		addSpace();
		builder.append("Between ");
		builder.append(num + " ");
		return this;
	}

	public SQLBuilder Between(double num) {
		addSpace();
		builder.append("Between ");
		builder.append(num + " ");
		return this;
	}

	public SQLBuilder Between(String s) {
		addSpace();
		builder.append("Between ");
		handleString(s);
		builder.append(" ");
		return this;
	}

	public SQLBuilder Between(Calendar calendar, DateType type) {
		String time = convertDate(calendar, type);
		Between(time);
		return this;
	}

	public SQLBuilder Between(Calendar calendar) {
		Between(calendar, DateType.date);
		return this;
	}

	public SQLBuilder AND(String string) {
		addSpace();
		builder.append("AND ");
		builder.append(string);
		return this;
	}

	public SQLBuilder AND(Calendar calendar, DateType type) {
		addSpace();
		String time = convertDate(calendar, type);
		builder.append("AND ");
		handleString(time);
		return this;
	}

	public SQLBuilder AND(Calendar calendar) {
		AND(calendar, DateType.date);
		return this;
	}

	public SQLBuilder OR(String string) {
		addSpace();
		builder.append("OR ");
		builder.append(string);
		return this;
	}

	private void handleString(String string) {

		builder.append("'");
		builder.append(string);
		builder.append("'");

	}

	private void judgeKeyWord(String judgeWord) {
		builder.append(judgeWord);
	}

	public void clear() {
		if (builder != null) {
			builder.delete(0, builder.length());
		}
	}

	public String toString() {
		return builder.toString();
	}

}
