package com.alexhuang.spring.biz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexhuang.spring.tx.TxKafkaProducer;

/**
 * BeanService是以Service结尾的spring bean，不会进入BeanNameAutoProxyCreator切面
 *
 */
@Service
public class BeanService implements IBeanService {
	
	private @Autowired TxKafkaProducer txKafkaProducer;

	@Override
	public String test() {
		String kafkaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		txKafkaProducer.send("##" + kafkaDate + " in BeanService##");
		return "abc".substring(10, 2);
	}

}