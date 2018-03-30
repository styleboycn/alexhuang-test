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

public interface IMsgProcessBiz {

	void handleMsgBiz(MsgRequestDto msgRequest);
}
