package test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;



public class ObjToString {
	public static String toString(Object object) {
		String rawResult = new Inner().buildString(object, 0,true);
		return handleRawResult(rawResult);
	}
	
	private static String handleRawResult(String rawResult){
		String result = rawResult.replaceAll("\n\n+", "\n");
		if(result.charAt(0)=='\n'){
			result = result.substring(1);
		}
		if(result.charAt(result.length()-1)=='\n'){
			result = result.substring(0, result.length()-1);
		}
		return result;
	}

	static class Inner {
		// 这个set是为了防止对象相互引用的问题，处理对象前会查看一下是否已经存在
		HashSet<Object> objectSet = new HashSet<>();

		// indent：缩进
		private String buildString(Object object, int indent,boolean printName) {
			if (object == null) {
				return "null";
			}

			// 是基本类型或String或StringBuilder就返回
			try {
				Class clazz = (Class) object.getClass().getField("TYPE").get(null);
				if (clazz.isPrimitive()) {
					return object.toString();
				}
			} catch (Exception e) {
				if (object.getClass() == String.class || object.getClass() == StringBuilder.class) {
					return object.toString();
				}
			}

			// 确定不是基本类型，再确定是不是集合
			StringBuilder builder = new StringBuilder();
			builder.insert(0, '\n');
			if (printName) {
				for (int i = 0; i < indent; i++) {
					builder.append(spaceString);
				}
				builder.append(object.getClass().getSimpleName());
				builder.append(':');
			}
			// 看是否已经存在该对象在
			if (objectSet.contains(object)) {
				if(builder.charAt(0)=='\n'){
					builder.replace(0, 1, "");
				}
				builder.append("has printed,skip");
				return builder.toString();
			} else {
				objectSet.add(object);
			}
			builder.append('\n');

			// 是集合或者数组
			if (object instanceof Collection) {
				@SuppressWarnings("rawtypes")
				Collection collection = (Collection) object;
				for (Object oneObj : collection) {
					builder.append(buildString(oneObj, indent + 1,true));
					builder.append('\n');
				}
				return builder.toString();
			} else if (object.getClass().isArray()) {
				//builder.insert(0, '\n');
				int length = Array.getLength(object);
				for (int i = 0; i < length; i++) {
					builder.append(buildString(Array.get(object, i), indent + 1,true));
					builder.append('\n');
				}
				return builder.toString();
			}

			// 是普通对象
			//注意 getDeclaredFields是返回所有变量，而getFields是返回public变量
			builder.insert(0, '\n');
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field aField : fields) {
				//先设置为可访问
				aField.setAccessible(true);
				buildField(builder, aField, indent + 1);
				try {
					builder.append(buildString(aField.get(object), indent + 1,false));
					builder.append('\n');
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return builder.toString();

		}

		private void buildField(StringBuilder builder, Field field, int indent) {
			for (int i = 0; i < indent; i++) {
				builder.append(spaceString);
			}
			builder.append(field.getType().getSimpleName() + " " + field.getName() + ":");
		}

	}
	
	private static String spaceString = "      ";



}
