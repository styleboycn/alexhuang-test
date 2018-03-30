package com.alexhuang.qm;
/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年3月29日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

public interface IMessageHandler<T> {

	void handle(T dto, String json);
	
	String getKey(T dto);
	
	Integer getVersion(T dto);

}
