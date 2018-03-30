package com.alexhuang.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public enum MdmtrmJsonFeature {
	ALLOW_SINGLE_QUOTES(m -> m.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)), // 允许单引号
	ALLOW_UNQUOTED_FIELD_NAMES(m -> m.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)), // 允许属性无引号
	CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES(m -> m.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)), // json中属性用下划线，对象中用驼峰
	;

	private IAddConfig adder;

	MdmtrmJsonFeature(IAddConfig adder) {
		this.adder = adder;
	}

	public void config(ObjectMapper objectMapper) {
		this.adder.addConfig(objectMapper);
	}
}

interface IAddConfig {
	void addConfig(ObjectMapper objectMapper);
}