package com.alexhuang.qm;

import com.alexhuang.dao.domain.QueueMachineData;
import com.alexhuang.utils.json.JsonUtils;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年3月30日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

public class Context<T> {

	/** 消息的类信息 */
    Class<T> messageClass;
    
    /** 消息处理逻辑 */
    IMessageHandler<T> handler;
    
    /** 报文的json */
    String message;
    
    /** 报文decode后的对象 */
    T obj;
    
    /** 消息在数据库中的对象映射 */
    QueueMachineData queueMachineData;

    public Context(Class<T> messageClass, String message, IMessageHandler<T> handler) {
        this.messageClass = messageClass;
        this.handler = handler;
        decode(message);
    }

    public Integer getVersion() {
        return handler.getVersion(obj);
    }

    public String getKey() {
        return handler.getKey(obj);
    }

    public void handle() {
        handler.handle(obj, message);
    }

    public void decode(String message) {
        this.message = message;
        this.obj = JsonUtils.json2Object(message, messageClass);
    }
    
}
