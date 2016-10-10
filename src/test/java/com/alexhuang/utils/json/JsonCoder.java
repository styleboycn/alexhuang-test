package com.alexhuang.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonCoder {

	private static Logger logger = LoggerFactory.getLogger(JsonCoder.class);

	private final ObjectMapper objectMapper;
	/** 格式化时间的string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	{
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国北京时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public JsonCoder(){}

	public JsonCoder(MdmtrmJsonFeature... features) {
		for (MdmtrmJsonFeature iff : features) {
			iff.config(objectMapper);
		}
	}

	/**
	 * json 转换成 Object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> T json2Object(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("", e);
			throw new JsonException("解析json错误", e);
		}
	}

	public <T> T json2Object(String json, TypeRef<T> tr) {
		try {
			return objectMapper.readValue(json, TypeFactory.defaultInstance().constructType(tr.getType()));
		} catch (IOException e) {
			logger.error("", e);
			throw new JsonException("解析json错误", e);
		}
	}

	/**
	 * obj 转换成json
	 * 
	 * @param entity
	 * @return
	 */
	public <T> String object2Json(T entity) {
		try {
			return objectMapper.writeValueAsString(entity);
		} catch (IOException e) {
			logger.error("", e);
			throw new JsonException("转换json错误");
		}
	}
	
}
