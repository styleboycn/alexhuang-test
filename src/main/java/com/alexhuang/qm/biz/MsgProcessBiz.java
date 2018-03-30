package com.alexhuang.qm.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.qm.IMessageHandler;
import com.alexhuang.qm.QueueMachine;
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

@Component
public class MsgProcessBiz implements IMsgProcessBiz {
	
    @Autowired
    private QueueMachine queueMachine;

	@Override
	public void handleMsgBiz(MsgRequestDto msgRequest) {
		
		String dtoToJson = JsonUtils.object2Json(msgRequest);
		queueMachine.execute(MsgRequestDto.class, dtoToJson,
				
				new IMessageHandler<MsgRequestDto>() {

					@Override
					public void handle(MsgRequestDto dto, String json) {
						System.out.printf("execute biz logic, dto 's key : %s, dto : %s", dto.getMsgKey(), dto.getMsgVersion());
						System.out.println();
					}

					@Override
					public String getKey(MsgRequestDto dto) {
						return dto.getMsgKey();
					}

					@Override
					public Integer getVersion(MsgRequestDto dto) {
						return dto.getMsgVersion();
					}

				});
			
	}

}
