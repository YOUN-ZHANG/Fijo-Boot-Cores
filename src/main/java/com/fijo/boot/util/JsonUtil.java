package com.fijo.boot.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static String convertObj2String(Object object) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static <T> T convertString2Obj(String s, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(s, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
	
	/**
	 * object转json字符串
	 * @param object
	 * @return
	 */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    /**
     * object转json字符串 非序列化
     * @param object
     * @return
     */
    public static String toJsonNoSerialize(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
    /**
     * list<Object> 转为json字符串
     * @param list
     * @return
     */
    public static String listObj2Json(List<Object> list){
    	GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
    	Gson gson2= gsonBuilder.create();
    	List paramList = new ArrayList();
    	for(Object obj : list){
    		paramList.add(toJson(obj));
    	}
    	String str=gson2.toJson(list);
    	return str;
    }
    
    /**
     * string类型转json对象（单层）
     * @param jsonstr
     * @return
     */
    public static JSONObject str2JsonObj(String jsonstr) {
    	JSONObject obj =  JSONObject.fromObject(jsonstr);
    	return obj;
    }
     
    /**
     * json类型字符串转bean
     * @param strJsonData
     * @param cls
     * @return
     */
    public static <T> T json2Bean(String strJsonData,Class<T> cls){
    	Gson gson = new Gson();
    	T t = gson.fromJson(strJsonData, cls);
    	return t;
    }
    
    /**
     * json转list结构
     * @param strJsonData
     * @param T
     * @return
     */
    public static List<?> json2BeanList(String strJsonData,Class<?>T){
    	Gson gson = new Gson();
    	List<?> list = gson.fromJson(strJsonData, new TypeToken<List<?>>(){}.getType());
    	if(list == null)
    		return null;
    	return list;
    }
    
    /**
     * json转bean,List<?>通用方法
     * @param strJsonData
     * @param cls
     * @return
     */
    public static <T> T json2List(String strJsonData, Class<?> cls){
    	Gson gson = new Gson();
    	T t = gson.fromJson(strJsonData, new TypeToken<T>(){}.getType());
    	return t;
    }
    
    
    /**
     * 使用Gson拍平json字符串，即当有多层json嵌套时，可以把多层的json拍平为一层
     * @param map
     * @param json
     * @param parentKey
     */
    public static void parseJson2Map(Map map, String json, String parentKey){
        JsonElement jsonElement = new JsonParser().parse(json);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            parseJson2Map(map,jsonObject,parentKey);
            //传入的还是一个json数组
        }else if (jsonElement.isJsonArray()){
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Iterator<JsonElement> iterator = jsonArray.iterator();
            while (iterator.hasNext()){
                parseJson2Map(map,iterator.next().getAsJsonObject(),parentKey);
            }
        }else if (jsonElement.isJsonPrimitive()){
            System.out.println("please check the json format!");
        }else if (jsonElement.isJsonNull()){

        }
    }

    public static void parseJson2Map(Map map, JsonObject jsonObject, String parentKey){
        for (Map.Entry<String, JsonElement> object : jsonObject.entrySet()) {
            String key = object.getKey();
            JsonElement value = object.getValue();
            String fullkey = (null == parentKey || parentKey.trim().equals("")) ? key : key;
            //判断对象的类型，如果是空类型则安装空类型处理
            if (value.isJsonNull()){
                map.put(fullkey,null);
                continue;
            //如果是JsonObject对象则递归处理
            }else if (value.isJsonObject()){
                parseJson2Map(map,value.getAsJsonObject(),fullkey);
            //如果是JsonArray数组则迭代，然后进行递归
            }else if (value.isJsonArray()){
                JsonArray jsonArray = value.getAsJsonArray();
                Iterator<JsonElement> iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JsonElement jsonElement1 = iterator.next();
                    parseJson2Map(map, jsonElement1.getAsJsonObject(), fullkey);
                }
                continue;
             // 如果是JsonPrimitive对象则获取当中的值,则还需要再次进行判断一下
            }else if (value.isJsonPrimitive()){
                try {
                    JsonElement element = new JsonParser().parse(value.getAsString());
                    if (element.isJsonNull()){
                        map.put(fullkey,value.getAsString());
                    }else if (element.isJsonObject()) {
                        parseJson2Map(map, element.getAsJsonObject(), fullkey);
                    } else if (element.isJsonPrimitive()) {
                        JsonPrimitive jsonPrimitive = element.getAsJsonPrimitive();

                        if (jsonPrimitive.isNumber()) {
                            map.put(fullkey, jsonPrimitive.getAsNumber());
                        } else {
                            map.put(fullkey, jsonPrimitive.getAsString());
                        }
                    } else if (element.isJsonArray()) {
                        JsonArray jsonArray = element.getAsJsonArray();
                        Iterator<JsonElement> iterator = jsonArray.iterator();
                        while (iterator.hasNext()) {
                            parseJson2Map(map, iterator.next().getAsJsonObject(), fullkey);
                        }
                    }
                }catch (Exception e){
                    map.put(fullkey,value.getAsString());
                }
            }
        }
    }
    
    
 
    
    /**
     * 使用Gson拍平json字符串，即当有多层json嵌套时，可以把多层的json拍平为一层
     * @param map
     * @param json
     * @param parentKey
     */
    public static void parseJson2List(List<Map<String, Object>> list, String json, String parentKey){
        JsonElement jsonElement = new JsonParser().parse(json);
        
        if (jsonElement.isJsonObject()) {
        	Map map = new HashMap<>();
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            parseJson2Map(map,jsonObject,parentKey);
            //传入的还是一个json数组
        }else if (jsonElement.isJsonArray()){
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Iterator<JsonElement> iterator = jsonArray.iterator();
            while (iterator.hasNext()){
            	Map map = new HashMap<>();
            	parseJson2Map(map,iterator.next().getAsJsonObject(),parentKey);
            	list.add(map);
            }
        }else if (jsonElement.isJsonPrimitive()){
            System.out.println("please check the json format!");
        }else if (jsonElement.isJsonNull()){

        }
    }
    
	/**
	 *把拍平后的json进行格式化处理，输出标准的json格式
	 * @param uglyJSONString
	 * @return
	 */
	public static List jsonFormatter(String uglyJSONString){
	
	    Map<String,Object> map = new HashMap<>();
	    List<Map<String, Object>> list = new ArrayList<>();
	    parseJson2List(list,uglyJSONString,null);
	    List resultList = new ArrayList<>();
	    Gson gson = new Gson();
	    for(int i = 0; i < list.size();i++){
	    	JSONObject json = JSONObject.fromObject(list.get(i));
	    	resultList.add(json);
	    }
	    ///System.out.println(prettyJsonString);
	    return resultList;
	}

	
}
