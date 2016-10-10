package com.alexhuang.utils.json;

public class JsonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4051778567054781546L;
	
	public JsonException(String msg) {
		super(msg);
	}
	
	public JsonException(String msg, Throwable e) {
		super(msg, e);
	}

}
