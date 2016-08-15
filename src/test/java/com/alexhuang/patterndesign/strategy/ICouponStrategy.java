package com.alexhuang.patterndesign.strategy;

//优惠券策略
public interface ICouponStrategy {

	public double calcPrice(double booksPrice);
	
}
