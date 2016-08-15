package com.alexhuang.patterndesign.strategy;

//优惠券策略
public class PinkCouponStrategy implements ICouponStrategy {

	public double calcPrice(double booksPrice) {
		System.out.println("this is PinkCouponStrategy!");
		return booksPrice - 50; 
	}
	
}
