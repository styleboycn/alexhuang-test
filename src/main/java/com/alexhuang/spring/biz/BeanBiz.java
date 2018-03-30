package com.alexhuang.spring.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alexhuang.spring.tx.TxKafkaProducer;

/**
 * BeanBiz是以Biz结尾的spring bean，会进入BeanNameAutoProxyCreator切面
 *
 */
@Component
public class BeanBiz implements IBeanBiz {
	
	private @Autowired TxKafkaProducer txKafkaProducer;

	@Override
	public String test() {
		String kafkaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		txKafkaProducer.send("##" + kafkaDate + " in BeanBiz##");
		return "abc".substring(10, 2);
	}

}
