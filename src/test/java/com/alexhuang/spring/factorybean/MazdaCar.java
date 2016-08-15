package com.alexhuang.spring.factorybean;

public class MazdaCar implements ICar {
	
	private String carName;
	
	private double carPrice;
	

	public MazdaCar(String carName, double carPrice) {
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
		System.out.println("my name is Mazda, from Japan!!!");
	}

	
}
