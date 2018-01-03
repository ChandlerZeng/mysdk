package com.ddt.main.service.util;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class JSONHelper {

	/**
	 * 灏嗗璞¤浆鎹㈡垚Json瀛楃涓�
	 * @param obj
	 * @return json绫诲瀷瀛楃涓�
	 */
	public static String toJSON(Object obj) {
		JSONStringer js = new JSONStringer();
		serialize(js, obj);
		return js.toString();
	}

	/**
	 * 搴忓垪鍖栦负JSON
	 * @param js json瀵硅薄
	 * @param o	寰呴渶搴忓垪鍖栫殑瀵硅薄
	 */
	private static void serialize(JSONStringer js, Object o) {
		if (isNull(o)) {
			try {
				js.value(null);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return;
		}

		Class<?> clazz = o.getClass();
		if (isObject(clazz)) { // 瀵硅薄
			serializeObject(js, o);
		} else if (isArray(clazz)) { // 鏁扮粍
			serializeArray(js, o);
		} else if (isCollection(clazz)) { // 闆嗗悎
			Collection<?> collection = (Collection<?>) o;
			serializeCollect(js, collection);
		}else if (isMap(clazz)) { // 闆嗗悎
			HashMap<?,?> collection = (HashMap<?,?>) o;
			serializeMap(js, collection);
		} else { // 鍗曚釜鍊�
			try {
				js.value(o);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 搴忓垪鍖栨暟缁�
	 * @param js	json瀵硅薄
	 * @param array	鏁扮粍
	 */
	private static void serializeArray(JSONStringer js, Object array) {
		try {
			js.array();
			for (int i = 0; i < Array.getLength(array); ++i) {
				Object o = Array.get(array, i);
				serialize(js, o);
			}
			js.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 搴忓垪鍖栭泦鍚�
	 * @param js	json瀵硅薄
	 * @param collection	闆嗗悎
	 */
	private static void serializeCollect(JSONStringer js, Collection<?> collection) {
		try {
			js.array();
			for (Object o : collection) {
				serialize(js, o);
			}
			js.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 搴忓垪鍖朚ap
	 * @param js	json瀵硅薄
	 * @param map	map瀵硅薄
	 */
	private static void serializeMap(JSONStringer js, Map<?,?> map) {
		try {
			js.object();
			@SuppressWarnings("unchecked")
			Map<String, Object> valueMap = (Map<String, Object>) map;
			Iterator<Map.Entry<String, Object>> it = valueMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>)it.next();
				js.key(entry.getKey());
				serialize(js,entry.getValue());
			}
			js.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 搴忓垪鍖栧璞�
	 * @param js	json瀵硅薄
	 * @param obj	寰呭簭鍒楀寲瀵硅薄
	 */
	private static void serializeObject(JSONStringer js, Object obj) {
		try {
			js.object();
			Class<? extends Object> objClazz = obj.getClass();
			Method[] methods = objClazz.getDeclaredMethods();   
	        Field[] fields = objClazz.getDeclaredFields();     
	        for (Field field : fields) {   
	            try {   
	                String fieldType = field.getType().getSimpleName();   
	                String fieldGetName = parseMethodName(field.getName(),"get");   
	                if (!haveMethod(methods, fieldGetName)) {   
	                    continue;   
	                }   
	                Method fieldGetMet = objClazz.getMethod(fieldGetName, new Class[] {});   
	                Object fieldVal = fieldGetMet.invoke(obj, new Object[] {});   
	                String result = null;   
	                if ("Date".equals(fieldType)) {   
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);   
	                    result = sdf.format((Date)fieldVal);  

	                } else {   
	                    if (null != fieldVal) {   
	                        result = String.valueOf(fieldVal);   
	                    }   
	                }   
	                js.key(field.getName());
					serialize(js, result);  
	            } catch (Exception e) {   
	                continue;   
	            }   
	        }  
			js.endObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 鍒ゆ柇鏄惁瀛樺湪鏌愬睘鎬х殑 get鏂规硶
	 * @param methods	寮曠敤鏂规硶鐨勬暟缁�
	 * @param fieldMethod	鏂规硶鍚嶇О
	 * @return true鎴栬�false
	 */
	public static boolean haveMethod(Method[] methods, String fieldMethod) {
		for (Method met : methods) {
			if (fieldMethod.equals(met.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 鎷兼帴鏌愬睘鎬х殑 get鎴栬�set鏂规硶
	 * @param fieldName	瀛楁鍚嶇О
	 * @param methodType	鏂规硶绫诲瀷
	 * @return 鏂规硶鍚嶇О
	 */
	public static String parseMethodName(String fieldName,String methodType) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return methodType + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**  
     * 缁欏瓧娈佃祴鍊� 
     * @param obj  瀹炰緥瀵硅薄
     * @param valMap  鍊奸泦鍚�
     */  
    public static void setFieldValue(Object obj, Map<String, String> valMap) {   
        Class<?> cls = obj.getClass();   
        // 鍙栧嚭bean閲岀殑鎵�湁鏂规硶   
        Method[] methods = cls.getDeclaredMethods();   
        Field[] fields = cls.getDeclaredFields();   
  
        for (Field field : fields) {   
            try {     
                String setMetodName = parseMethodName(field.getName(),"set");   
                if (!haveMethod(methods, setMetodName)) {   
                    continue;   
                }   
                Method fieldMethod = cls.getMethod(setMetodName, field   
                        .getType());   
                String value = valMap.get(field.getName());   
                if (null != value && !"".equals(value)) {   
                    String fieldType = field.getType().getSimpleName();   
                    if ("String".equals(fieldType)) {   
                        fieldMethod.invoke(obj, value);   
                    } else if ("Date".equals(fieldType)) {   
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);   
                        Date temp = sdf.parse(value);    
                        fieldMethod.invoke(obj, temp);   
                    } else if ("Integer".equals(fieldType)   
                            || "int".equals(fieldType)) {   
                        Integer intval = Integer.parseInt(value);   
                        fieldMethod.invoke(obj, intval);   
                    } else if ("Long".equalsIgnoreCase(fieldType)) {   
                        Long temp = Long.parseLong(value);   
                        fieldMethod.invoke(obj, temp);   
                    } else if ("Double".equalsIgnoreCase(fieldType)) {   
                        Double temp = Double.parseDouble(value);   
                        fieldMethod.invoke(obj, temp);   
                    } else if ("Boolean".equalsIgnoreCase(fieldType)) {   
                        Boolean temp = Boolean.parseBoolean(value);   
                        fieldMethod.invoke(obj, temp);   
                    } else {   
                        System.out.println("setFieldValue not supper type:" + fieldType);   
                    }   
                }   
            } catch (Exception e) {   
                continue;   
            }   
        }   
  
    } 
    
    /**
     * bean瀵硅薄杞琈ap
     * @param obj	瀹炰緥瀵硅薄
     * @return	map闆嗗悎
     */
    public static Map<String, String> beanToMap(Object obj) {   
        Class<?> cls = obj.getClass();   
        Map<String, String> valueMap = new HashMap<String, String>();   
        // 鍙栧嚭bean閲岀殑鎵�湁鏂规硶   
        Method[] methods = cls.getDeclaredMethods();   
        Field[] fields = cls.getDeclaredFields();     
        for (Field field : fields) {   
            try {   
                String fieldType = field.getType().getSimpleName();   
                String fieldGetName = parseMethodName(field.getName(),"get");   
                if (!haveMethod(methods, fieldGetName)) {   
                    continue;   
                }   
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[] {});   
                Object fieldVal = fieldGetMet.invoke(obj, new Object[] {});   
                String result = null;   
                if ("Date".equals(fieldType)) {   
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);   
                    result = sdf.format((Date)fieldVal);   

                } else {   
                    if (null != fieldVal) {   
                        result = String.valueOf(fieldVal);   
                    }   
                }   
                valueMap.put(field.getName(), result);   
            } catch (Exception e) {   
                continue;   
            }   
        }   
        return valueMap;   
  
    }   

	/**
	 * 缁欏璞＄殑瀛楁璧嬪�
	 * @param obj	绫诲疄渚�
	 * @param fieldSetMethod	瀛楁鏂规硶
	 * @param fieldType	瀛楁绫诲瀷
	 * @param value
	 */
	public static void setFiedlValue(Object obj,Method fieldSetMethod,String fieldType,Object value){
		   
        try {    
            if (null != value && !"".equals(value)) {    
            	if ("String".equals(fieldType)) {   
                	fieldSetMethod.invoke(obj, value.toString());   
                } else if ("Date".equals(fieldType)) {   
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);   
                    Date temp = sdf.parse(value.toString());    
                    fieldSetMethod.invoke(obj, temp);   
                } else if ("Integer".equals(fieldType)   
                        || "int".equals(fieldType)) {   
                    Integer intval = Integer.parseInt(value.toString());   
                    fieldSetMethod.invoke(obj, intval);   
                } else if ("Long".equalsIgnoreCase(fieldType)) {   
                    Long temp = Long.parseLong(value.toString());   
                    fieldSetMethod.invoke(obj, temp);   
                } else if ("Double".equalsIgnoreCase(fieldType)) {   
                    Double temp = Double.parseDouble(value.toString());   
                    fieldSetMethod.invoke(obj, temp);   
                } else if ("Boolean".equalsIgnoreCase(fieldType)) {   
                    Boolean temp = Boolean.parseBoolean(value.toString());   
                    fieldSetMethod.invoke(obj, temp);   
                } else {   
                	fieldSetMethod.invoke(obj, value); 
                } 
			}
                
        } catch (Exception e) {   
        	e.printStackTrace();
        }   
    
	}
	
	/**
	 * 鍙嶅簭鍒楀寲绠�崟瀵硅薄
	 * @param jo	json瀵硅薄
	 * @param clazz	瀹炰綋绫荤被鍨�
	 * @return	鍙嶅簭鍒楀寲鍚庣殑瀹炰緥
	 * @throws JSONException 
	 */
	public static <T> T parseObject(JSONObject jo, Class<T> clazz) throws JSONException {
		if (clazz == null || isNull(jo)) {
			return null;
		}

		T obj = newInstance(clazz);
		if (obj == null) {
			return null;
		}
		if(isMap(clazz)){ 
			setField(obj,jo);
		}else{
			  // 鍙栧嚭bean閲岀殑鎵�湁鏂规硶   
	        Method[] methods = clazz.getDeclaredMethods();   
	        Field[] fields = clazz.getDeclaredFields();   	        
			for (Field f : fields) {
				String setMetodName = parseMethodName(f.getName(),"set");   
                if (!haveMethod(methods, setMetodName)) {   
                    continue;   
                }                 
				try {
					Method fieldMethod = clazz.getMethod(setMetodName, f.getType());
					setField(obj,fieldMethod,f, jo);
				} catch (Exception e) {
					e.printStackTrace();
				}  
			}
		}
		return obj;
	}
	
	/**
	 * 鍙嶅簭鍒楀寲绠�崟瀵硅薄
	 * @param jsonStr	json瀛楃涓�
	 * @param clazz	瀹炰綋绫荤被鍨�
	 * @return	鍙嶅簭鍒楀寲鍚庣殑瀹炰緥
	 * @throws JSONException 
	 */
	public static <T> T parseObject(String jsonStr, Class<T> clazz) throws JSONException {
		if (clazz == null || jsonStr == null || jsonStr.length() == 0) {
			return null;
		}
		
		JSONObject jo = null;
		jo = new JSONObject(jsonStr);
		if (isNull(jo)) {
			return null;
		}

		return parseObject(jo, clazz);
	}

	/**
	 * 鍙嶅簭鍒楀寲鏁扮粍瀵硅薄
	 * @param ja	json鏁扮粍
	 * @param clazz	瀹炰綋绫荤被鍨�
	 * @return	鍙嶅簭鍒楀寲鍚庣殑鏁扮粍
	 */
	public static <T> T[] parseArray(JSONArray ja, Class<T> clazz) {
		if (clazz == null || isNull(ja)) {
			return null;
		}

		int len = ja.length();

		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, len);

		for (int i = 0; i < len; ++i) {
			try {
				JSONObject jo = ja.getJSONObject(i);
				T o = parseObject(jo, clazz);
				array[i] = o;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return array;
	}

	
	/**
	 * 鍙嶅簭鍒楀寲鏁扮粍瀵硅薄
	 * @param jsonStr	json瀛楃涓�
	 * @param clazz	瀹炰綋绫荤被鍨�
	 * @return	搴忓垪鍖栧悗鐨勬暟缁�
	 */
	public static <T> T[] parseArray(String jsonStr, Class<T> clazz) {
		if (clazz == null || jsonStr == null || jsonStr.length() == 0) {
			return null;
		}
		JSONArray jo = null;
		try {
			jo = new JSONArray(jsonStr);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (isNull(jo)) {
			return null;
		}

		return parseArray(jo, clazz);
	}

	/**
	 * 鍙嶅簭鍒楀寲娉涘瀷闆嗗悎
	 * @param ja	json鏁扮粍
	 * @param collectionClazz	闆嗗悎绫诲瀷
	 * @param genericType	瀹炰綋绫荤被鍨�
	 * @return
	 * @throws JSONException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> parseCollection(JSONArray ja, Class<?> collectionClazz,
			Class<T> genericType) throws JSONException {

		if (collectionClazz == null || genericType == null || isNull(ja)) {
			return null;
		}

		Collection<T> collection = (Collection<T>) newInstance(collectionClazz);

		for (int i = 0; i < ja.length(); ++i) {
			try {
				JSONObject jo = ja.getJSONObject(i);
				T o = parseObject(jo, genericType);
				collection.add(o);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return collection;
	}

	/**
	 * 鍙嶅簭鍒楀寲娉涘瀷闆嗗悎
	 * @param jsonStr	json瀛楃涓�
	 * @param collectionClazz	闆嗗悎绫诲瀷
	 * @param genericType	瀹炰綋绫荤被鍨�
	 * @return	鍙嶅簭鍒楀寲鍚庣殑鏁扮粍
	 * @throws JSONException 
	 */
	public static <T> Collection<T> parseCollection(String jsonStr, Class<?> collectionClazz,
			Class<T> genericType) throws JSONException {
		if (collectionClazz == null || genericType == null || jsonStr == null
				|| jsonStr.length() == 0) {
			return null;
		}
		JSONArray jo = null;
		try {
			//濡傛灉涓烘暟缁勶紝鍒欐澶勮浆鍖栨椂锛岄渶瑕佸幓鎺夊墠闈㈢殑閿紝鐩存帴鍚庨潰鐨刐]涓殑鍊�
			int index = jsonStr.indexOf("[");
			String arrayString=null; 
			
			//鑾峰彇鏁扮粍鐨勫瓧绗︿覆
			if(-1!=index){
				arrayString = jsonStr.substring(index);
			}
			
			//濡傛灉涓烘暟缁勶紝浣跨敤鏁扮粍杞寲
			if(null!=arrayString){
				jo = new JSONArray(arrayString);
			}
			else{
				jo = new JSONArray(jsonStr);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (isNull(jo)) {
			return null;
		}

		return parseCollection(jo, collectionClazz, genericType);
	}

	/**
	 * 鏍规嵁绫诲瀷鍒涘缓瀵硅薄
	 * @param clazz	寰呭垱寤哄疄渚嬬殑绫诲瀷
	 * @return	瀹炰緥瀵硅薄
	 * @throws JSONException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T> T newInstance(Class<T> clazz) throws JSONException {
		if (clazz == null)
			return null;
		T obj = null;
		if (clazz.isInterface()) {
			if (clazz.equals(Map.class)) {
				obj = (T) new HashMap();
			}else if (clazz.equals(List.class)) {
				obj = (T) new ArrayList();
			}else if (clazz.equals(Set.class)) {
				obj = (T) new HashSet();
			}else{
				throw new JSONException("unknown interface: " + clazz);
			}
		}else{
			try {
				obj = clazz.newInstance();
			}catch (Exception e) {
				throw new JSONException("unknown class type: " + clazz);
			}
		}	
		return obj;
	}
	
	/**
	 * 璁惧畾Map鐨勫�
	 * @param obj	寰呰祴鍊煎瓧娈电殑瀵硅薄
	 * @param jo	json瀹炰緥
	 */
	private static void setField(Object obj, JSONObject jo) {
		try {
			@SuppressWarnings("unchecked")
			Iterator<String> keyIter = jo.keys();
			String key;
			Object value;
			@SuppressWarnings("unchecked")
			Map<String, Object> valueMap = (Map<String, Object>) obj;
			while (keyIter.hasNext()) {
				key = (String) keyIter.next();
				value = jo.get(key);				
				valueMap.put(key, value);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * 璁惧畾瀛楁鐨勫�
	 * @param obj	寰呰祴鍊煎瓧娈电殑瀵硅薄
	 * @param fieldSetMethod	瀛楁鏂规硶鍚�
	 * @param field	瀛楁
	 * @param jo	json瀹炰緥
	 */
	private static void setField(Object obj, Method fieldSetMethod,Field field, JSONObject jo) {
		String name = field.getName();
		Class<?> clazz = field.getType();		
		try {
			if (isArray(clazz)) { // 鏁扮粍
				Class<?> c = clazz.getComponentType();
				JSONArray ja = jo.optJSONArray(name);
				if (!isNull(ja)) {
					Object array = parseArray(ja, c);
					setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), array);
				}
			} else if (isCollection(clazz)) { // 娉涘瀷闆嗗悎
				// 鑾峰彇瀹氫箟鐨勬硾鍨嬬被鍨�
				Class<?> c = null;
				Type gType = field.getGenericType();
				if (gType instanceof ParameterizedType) {
					ParameterizedType ptype = (ParameterizedType) gType;
					Type[] targs = ptype.getActualTypeArguments();
					if (targs != null && targs.length > 0) {
						Type t = targs[0];
						c = (Class<?>) t;
					}
				}

				JSONArray ja = jo.optJSONArray(name);
				if (!isNull(ja)) {
					Object o = parseCollection(ja, clazz, c);
					setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
				}
			} else if (isSingle(clazz)) { // 鍊肩被鍨�
				Object o = jo.opt(name);
				if (o != null) {
					setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
				}
			} else if (isObject(clazz)) { // 瀵硅薄
				JSONObject j = jo.optJSONObject(name);
				if (!isNull(j)) {
					Object o = parseObject(j, clazz);
					setFiedlValue(obj, fieldSetMethod, clazz.getSimpleName(), o);
				}
			} else if (isList(clazz)) { // 鍒楄〃
//				JSONObject j = jo.optJSONObject(name);
//				if (!isNull(j)) {
//					Object o = parseObject(j, clazz);
//					f.set(obj, o);
//				}
			} else {
				throw new Exception("unknow type!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 璁惧畾瀛楁鐨勫� 
	 * @param obj	寰呰祴鍊煎瓧娈电殑瀵硅薄
	 * @param field	瀛楁
	 * @param jo	json瀹炰緥
	 */
	@SuppressWarnings("unused")
	private static void setField(Object obj, Field field, JSONObject jo) {
		String name = field.getName();
		Class<?> clazz = field.getType();
		try {
			if (isArray(clazz)) { // 鏁扮粍
				Class<?> c = clazz.getComponentType();
				JSONArray ja = jo.optJSONArray(name);
				if (!isNull(ja)) {
					Object array = parseArray(ja, c);
					field.set(obj, array);
				}
			} else if (isCollection(clazz)) { // 娉涘瀷闆嗗悎
				// 鑾峰彇瀹氫箟鐨勬硾鍨嬬被鍨�
				Class<?> c = null;
				Type gType = field.getGenericType();
				if (gType instanceof ParameterizedType) {
					ParameterizedType ptype = (ParameterizedType) gType;
					Type[] targs = ptype.getActualTypeArguments();
					if (targs != null && targs.length > 0) {
						Type t = targs[0];
						c = (Class<?>) t;
					}
				}
				JSONArray ja = jo.optJSONArray(name);
				if (!isNull(ja)) {
					Object o = parseCollection(ja, clazz, c);
					field.set(obj, o);
				}
			} else if (isSingle(clazz)) { // 鍊肩被鍨�
				Object o = jo.opt(name);
				if (o != null) {
					field.set(obj, o);
				}
			} else if (isObject(clazz)) { // 瀵硅薄
				JSONObject j = jo.optJSONObject(name);
				if (!isNull(j)) {
					Object o = parseObject(j, clazz);
					field.set(obj, o);
				}
			} else if (isList(clazz)) { // 鍒楄〃
				JSONObject j = jo.optJSONObject(name);
				if (!isNull(j)) {
					Object o = parseObject(j, clazz);
					field.set(obj, o);
				}
			}else {
				throw new Exception("unknow type!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 鍒ゆ柇瀵硅薄鏄惁涓虹┖
	 * @param obj	瀹炰緥
	 * @return
	 */
	private static boolean isNull(Object obj) {
		if (obj instanceof JSONObject) {
			return JSONObject.NULL.equals(obj);
		}
		return obj == null;
	}

	/**
	 * 鍒ゆ柇鏄惁鏄�绫诲瀷 
	 * @param clazz	
	 * @return
	 */
	private static boolean isSingle(Class<?> clazz) {
		return isBoolean(clazz) || isNumber(clazz) || isString(clazz);
	}

	/**
	 * 鏄惁甯冨皵鍊�
	 * @param clazz	
	 * @return
	 */
	public static boolean isBoolean(Class<?> clazz) {
		return (clazz != null)
				&& ((Boolean.TYPE.isAssignableFrom(clazz)) || (Boolean.class
						.isAssignableFrom(clazz)));
	}

	/**
	 * 鏄惁鏁板� 
	 * @param clazz	
	 * @return
	 */
	public static boolean isNumber(Class<?> clazz) {
		return (clazz != null)
				&& ((Byte.TYPE.isAssignableFrom(clazz)) || (Short.TYPE.isAssignableFrom(clazz))
						|| (Integer.TYPE.isAssignableFrom(clazz))
						|| (Long.TYPE.isAssignableFrom(clazz))
						|| (Float.TYPE.isAssignableFrom(clazz))
						|| (Double.TYPE.isAssignableFrom(clazz)) || (Number.class
						.isAssignableFrom(clazz)));
	}

	/**
	 * 鍒ゆ柇鏄惁鏄瓧绗︿覆 
	 * @param clazz	
	 * @return
	 */
	public static boolean isString(Class<?> clazz) {
		return (clazz != null)
				&& ((String.class.isAssignableFrom(clazz))
						|| (Character.TYPE.isAssignableFrom(clazz)) || (Character.class
						.isAssignableFrom(clazz)));
	}

	/**
	 * 鍒ゆ柇鏄惁鏄璞�
	 * @param clazz	
	 * @return
	 */
	private static boolean isObject(Class<?> clazz) {
		return clazz != null && !isSingle(clazz) && !isArray(clazz) && !isCollection(clazz) && !isMap(clazz);
	}

	/**
	 * 鍒ゆ柇鏄惁鏄暟缁�
	 * @param clazz
	 * @return
	 */
	public static boolean isArray(Class<?> clazz) {
		return clazz != null && clazz.isArray();
	}

	/**
	 * 鍒ゆ柇鏄惁鏄泦鍚�
	 * @param clazz
	 * @return
	 */
	public static boolean isCollection(Class<?> clazz) {
		return clazz != null && Collection.class.isAssignableFrom(clazz);
	}
		
	/**
	 * 鍒ゆ柇鏄惁鏄疢ap
	 * @param clazz
	 * @return
	 */
	public static boolean isMap(Class<?> clazz) {
		return clazz != null && Map.class.isAssignableFrom(clazz);
	}
	
	/**
	 * 鍒ゆ柇鏄惁鏄垪琛�
	 * @param clazz
	 * @return
	 */
	public static boolean isList(Class<?> clazz) {
		return clazz != null && List.class.isAssignableFrom(clazz);
	}
	
}
