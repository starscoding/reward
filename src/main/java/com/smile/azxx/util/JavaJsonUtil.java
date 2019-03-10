package com.smile.azxx.util;

import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaJsonUtil {
	/*private static ObjectMapper mapper = new ObjectMapper();*/
	private static Gson gson = new Gson();
	
	
	/**
	 * ObjectMapper 使用失败，
	 * 原因： 如果属性是MAX_CODE这种类型，jackson序列化出来是max_CODE，造成结果解析错误。
	 * 后续如果解决这个问题，可以继续使用
	 */
//	static{
//		/**
//         * 该特性决定parser将是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）。 由于JSON标准说明书上面没有提到注释是否是合法的组成，所以这是一个非标准的特性；
//         * 尽管如此，这个特性还是被广泛地使用。
//         *
//         * 注意：该属性默认是false，因此必须显式允许，即通过JsonParser.Feature.ALLOW_COMMENTS 配置为true。
//         *
//         */
//		mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//		/**
//         * 这个特性决定parser是否将允许使用非双引号属性名字， （这种形式在Javascript中被允许，但是JSON标准说明书中没有）。
//         *
//         * 注意：由于JSON标准上需要为属性名称使用双引号，所以这也是一个非标准特性，默认是false的。
//         * 同样，需要设置JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES为true，打开该特性。
//         *
//         */
//		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//		/**
//         * 该特性决定parser是否允许单引号来包住属性名称和字符串值。
//         *
//         * 注意：默认下，该属性也是关闭的。需要设置JsonParser.Feature.ALLOW_SINGLE_QUOTES为true
//         *
//         */
//		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//		/**
//         * 该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。 如果该属性关闭，则如果遇到这些字符，则会抛出异常。
//         * JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性。
//         *
//         * 注意：默认时候，该属性关闭的。需要设置：JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS为true。
//         *
//         */
//		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//		/**
//         * 该特性决定JSON对象属性名称是否可以被String#intern 规范化表示。
//         *
//         * 如果允许，则JSON所有的属性名将会 intern() ；如果不设置，则不会规范化，
//         *
//         * 默认下，该属性是开放的。此外，必须设置CANONICALIZE_FIELD_NAMES为true
//         *
//         * 关于intern方法作用：当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串 （该对象由 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String
//         * 对象添加到池中， 并且返回此 String 对象的引用。
//         *
//         * @since 1.3
//         */
//		mapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, false);
//		mapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, false);
//		
//		/**
//         * 该特性决定了当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler），是否应该抛出一个
//         * JsonMappingException异常。这个特性一般式所有其他处理方法对未知属性处理都无效后才被尝试，属性保留未处理状态。
//         *
//         * 默认情况下，该设置是被打开的。
//         *
//         * @since 1.2
//         */
//		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		
//		/*mapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {  
//		    反序列化时调用  
//		    @Override  
//		    public String nameForSetterMethod(MapperConfig<?> config,  
//		            AnnotatedMethod method, String defaultName) {  
//		        return method.getName().substring(3);  
//		    }  
//		     序列化时调用  
//		    @Override  
//		    public String nameForGetterMethod(MapperConfig<?> config,  
//		            AnnotatedMethod method, String defaultName) {  
//		        return method.getName().substring(3);  
//		    }  
//		});*/
//		
//		/*mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector());*/
//	}
	
	public static String beanToJson(Object o) {
        /*try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				return gson.toJson(o);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				return JSONObject.fromObject(o).toString();
			}
		}*/
		try {
			return gson.toJson(o);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			return JSONObject.fromObject(o).toString();
		}
	}

	public static String arrayToJson(Object o) {
		return JSONArray.fromObject(o).toString();
	}
	
	public static Object jsonToBean(String json) {
		return jsonToBean(json,JSONObject.class);
	}

	public static Object jsonToBean(String json, Class clazz) {
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(json);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(clazz);
		return JSONSerializer.toJava(jsonObject, jsonConfig);
	}

	public static Object jsonToBean(String json, Class clazz, Map map) {
		if (map == null)
			return jsonToBean(json, clazz);
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(json);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(clazz);
		jsonConfig.setClassMap(map);
		return JSONSerializer.toJava(jsonObject, jsonConfig);
	}

	public static Object jsonToBeanWithCollection(String json, Class clazz,
                                                  String collectionName, Class clazzType) {
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put(collectionName, clazzType);
		return jsonToBean(json, clazz, classMap);
		/*JSONObject jsonObject = JSONObject.fromObject(json);
		return JSONObject.toBean(jsonObject, clazz, classMap);*/
	}

	public static Object jsonToBeanWithCollection(String json, Class clazz,
                                                  Map<String, Class> classMap) {
		return jsonToBean(json, clazz, classMap);
		/*JSONObject jsonObject = JSONObject.fromObject(json);
		return JSONObject.toBean(jsonObject, clazz, classMap);*/
	}

	public static Object applyFieldsToBean(JSONObject o1, Object o2) {
		Set<String> keys = o1.keySet();
		Class<?> clz = o2.getClass();
		Object[] emptyArgs = {};

		for (String key : keys) {
			Method getMethod = null;
			try {
				getMethod = clz.getMethod(getMethodName(key, "get"));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				try {
					getMethod = clz.getMethod(getMethodName(key, "is"));
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			if (getMethod != null) {
				try {
					Method setMethod = clz.getMethod(getMethodName(key, "set"),
							getMethod.getReturnType());
					setMethod.invoke(o2, o1.get(key));
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return o2;
	}

	public static String getMethodName(String fieldName, String head) {
		String name = head + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return name;
	}

	public static String[] getFieldName(String methodName) {
		String name = "";
		String head = "";
		if (methodName.startsWith("get")) {
			head = methodName.substring(0, 3);
			name = methodName.substring(3);
		} else if (methodName.startsWith("is")) {
			head = methodName.substring(0, 2);
			name = methodName.substring(2);
		}
		if (StringUtils.isNotBlank(name)) {
			name = name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		String[] arr = { head, name };
		return arr;
	}
}
