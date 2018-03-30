package com.alexhuang.qm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alexhuang.qm.biz.IMsgProcessBiz;
import com.alexhuang.qm.biz.MsgRequestDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:qm-datasource.xml")
public class testQm {
	
    @Autowired
    private IMsgProcessBiz msgProcessBiz;

	static MsgRequestDto createDto(String keyValue, int version) {
		MsgRequestDto dto = new MsgRequestDto();
		dto.setMsgKey(keyValue);
		dto.setMsgVersion(version);
		dto.setBizField1("this msg key : " + dto.getMsgKey() + "，msg verison : " + dto.getMsgVersion());
		return dto;
	}
    
	@Test
	public void test1() throws Exception {
		System.out.println("。。。。。。。。。。。。。。。。。。。。。。。。。");
		MsgRequestDto dto1 = createDto("key1", 1);
		msgProcessBiz.handleMsgBiz(dto1);
	}

	@Test
	public void test2() throws Exception {
		System.out.println("。。。。。。。。。。。。。。。。。。。。。。。。。");
		msgProcessBiz.handleMsgBiz(createDto("key147", 4));
		msgProcessBiz.handleMsgBiz(createDto("key147", 3));
		msgProcessBiz.handleMsgBiz(createDto("key147", 2));
		msgProcessBiz.handleMsgBiz(createDto("key147", 1));
		
		msgProcessBiz.handleMsgBiz(createDto("key258", 3));
		msgProcessBiz.handleMsgBiz(createDto("key258", 1));
	}

}
