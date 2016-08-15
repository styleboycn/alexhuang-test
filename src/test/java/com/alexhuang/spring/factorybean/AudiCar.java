package com.alexhuang.spring.factorybean;

public class AudiCar implements ICar {
	
	private String carName;
	
	private double carPrice;
	

	public AudiCar(String carName, double carPrice) {
		this.carName = carName;
		this.carPrice = carPrice;
	}
	
	@Override
	public String getCarName() {
		return this.carName;
	}

	@Override
	public double getCarPrice() {
		return carPrice;
	}

	@Override
	public void doSomething() {
		System.out.println("my name is Audi, from germany!!!");
	}

	
}
