package com.alexhuang.spring.factorybean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class InitBean implements InitializingBean {
	
	private @Autowired ICar audiCar;
	private @Autowired ICar mazdaCar;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		audiCar.doSomething();
		mazdaCar.doSomething();
	}
	
	public void setAudiCar(ICar audiCar) {
		this.audiCar = audiCar;
	}

	public void setMazdaCar(ICar mazdaCar) {
		this.mazdaCar = mazdaCar;
	}
	
}