package com.alexhuang.patterndesign.strategy;

//用户计价模块
public class UserPriceModule {
	
	private IMemberStrategy memberStrategy;
	
	private ICouponStrategy couponStratety;
	
	public UserPriceModule(IMemberStrategy memberStrategy, ICouponStrategy couponStratety) {
		this.memberStrategy = memberStrategy;
		this.couponStratety = couponStratety;
	}
	
	public double quote(double booksPrice){
        double result =  this.memberStrategy.calcPrice(booksPrice);
        result =  this.couponStratety.calcPrice(result);
        return result;
    }
}
