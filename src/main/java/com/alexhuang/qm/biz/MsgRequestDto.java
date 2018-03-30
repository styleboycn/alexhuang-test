package com.alexhuang.qm.biz;
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

public class MsgRequestDto {

	private String msgKey;
	
	private Integer msgVersion;
	
	private String bizField1;

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public Integer getMsgVersion() {
		return msgVersion;
	}

	public void setMsgVersion(Integer msgVersion) {
		this.msgVersion = msgVersion;
	}

	public String getBizField1() {
		return bizField1;
	}

	public void setBizField1(String bizField1) {
		this.bizField1 = bizField1;
	}
	
	
}
