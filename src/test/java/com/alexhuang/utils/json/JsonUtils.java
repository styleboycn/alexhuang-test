package com.alexhuang.utils.json;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static Feature[] features = {};
	
	static {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// 小并发，小处理量情况下，fastjson的速度不如gson和jackson。
		// 在并发量和处理量加大之后，fastjson的解析速度是最快的，生成json的速度和其它两个差不多。
	}

	/**
	 * json 转换成 Object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Object(String json, Class<T> clazz) {
		try {
			return JSON.parseObject(json, clazz, features);
		} catch (Exception e) {
			throw new JsonException("解析json错误: class: " + clazz.getName() + ", json: " + json, e);
		}
	}

	/**
     * json 转换成 集合
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> json2Array(String json, Class<T> clazz) {
        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            throw new JsonException("解析json错误: class: " + clazz.getName() + ", json: " + json, e);
        }
    }
	
	public static <T> T json2Object(String json, TypeRef<T> tr) {
		try {
			return JSON.parseObject(json, tr.getType(), features);
		} catch (Exception e) {
			logger.error("", e);
			throw new JsonException("解析json错误: type: " + tr.getType().getTypeName() + ", json: " + json, e);
		}
	}

    public static JsonNavigator json2Object(String json) {
        try {
            return new JsonNavigator(JSON.parseObject(json, features));
        } catch (Exception e) {
            throw new JsonException("解析json错误, json: " + json, e);
        }
    }
    
	/**
	 * obj 转换成json
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> String object2Json(T entity) {
		try {
			return JSON.toJSONString(entity, SerializerFeature.WriteDateUseDateFormat);
		} catch (Exception e) {
			logger.error("", e);
			throw new JsonException("转换json错误");
		}
	}

}
