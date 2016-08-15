package com.alexhuang.patterndesign.strategy;

//优惠券策略
public class BlueCouponStrategy implements ICouponStrategy {

	public double calcPrice(double booksPrice) {
		System.out.println("this is BlueCouponStrategy!");
		return booksPrice - 20; 
	}
	
}
