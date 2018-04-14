package com.alexhuang.patterndesign.strategy;

//会员级别策略
public interface IMemberStrategy {
	
	public double calcPrice(double booksPrice);
	
}
