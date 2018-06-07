package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.bridge.AbstractMessage;
import com.alexhuang.patterndesign.bridge.CommonMessage;
import com.alexhuang.patterndesign.bridge.MessageEmail;
import com.alexhuang.patterndesign.bridge.MessageImplementor;
import com.alexhuang.patterndesign.bridge.MessageSMS;
import com.alexhuang.patterndesign.bridge.UrgencyMessage;

/**
 * 桥梁模式的用意是“将抽象化(Abstraction)与实现化(Implementation)脱耦，使得二者可以独立地变化”。
 * 通过“消息类型”与“发送消息的方式”这个例子，采用桥梁模式来实现，抽象部分和实现部分分离开了，可以相互独立的变化，而不会相互影响。
 * 因此在抽象部分添加新的消息处理（特急消息），对发送消息的实现部分是没有影响的；反过来增加发送消息的方式（手机短消息），对消息处理部分也是没有影响的。
 * 《JAVA与模式》之桥梁模式
 * http://www.cnblogs.com/java-my-life/archive/2012/05/07/2480938.html
 */
public class BridgeTest {

	public static void main(String[] args) {
		// 创建具体的实现对象
		MessageImplementor impl = new MessageSMS();
		// 创建普通消息对象
		AbstractMessage message = new CommonMessage(impl);
		message.sendMessage("加班申请速批", "黄总");

		// 将实现方式切换成邮件，再次发送
		impl = new MessageEmail();
		// 创建加急消息对象
		message = new UrgencyMessage(impl);
		message.sendMessage("加班申请速批", "张总");
	}
	
}
