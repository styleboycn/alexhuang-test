package com.alexhuang.patterndesign.strategy;

public class Test1 {

	public static void main(String[] args) {

		IMemberStrategy strategy1 = new AdvanceMemberStrategy();
		ICouponStrategy strategy2 = new BlueCouponStrategy();
		
		UserPriceModule price = new UserPriceModule(strategy1, strategy2);
        
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
	
	}

}
