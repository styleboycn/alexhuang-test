package com.alexhuang.patterndesign.strategy;

//会员级别策略
public class CommonMemberStrategy implements IMemberStrategy {
	
	public double calcPrice(double booksPrice) {
		System.out.println("this is CommonMemberStrategy!");
		return booksPrice * 0.8;
	}
	
}
