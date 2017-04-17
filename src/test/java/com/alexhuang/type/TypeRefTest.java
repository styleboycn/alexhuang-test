package com.alexhuang.type;

import java.util.List;

import com.alexhuang.utils.json.JsonUtils;
import com.alexhuang.utils.json.TypeRef;


public class TypeRefTest {
	
	
	public static void main(String[] args) {
		TypeRef<List<IntStateConfig>> myType = new TypeRef<List<IntStateConfig>>() {};
		System.out.println(myType);
		String content = "[]";
		List<IntStateConfig> scList = JsonUtils.json2Object(content, myType);
		System.out.println(scList);
	}
}
