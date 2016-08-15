package com.alexhuang.patterndesign.strategy;

//会员级别策略
public class AdvanceMemberStrategy implements IMemberStrategy {
	
	public double calcPrice(double booksPrice) {
		System.out.println("this is AdvanceMemberStrategy!");
		return booksPrice * 0.6;
	}
	
}
