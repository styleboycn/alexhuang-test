package com.alexhuang.utils.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeRef<T> {
	private Type jt = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	/**
	 * 包内访问
	 * 
	 * @return
	 */
	Type getType() {
		return jt;
//		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
//		Type[] types = pType.getActualTypeArguments();
//		return types[0];
	}

}
