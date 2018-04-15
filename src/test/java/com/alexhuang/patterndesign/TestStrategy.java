package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.strategy.AdvanceMemberStrategy;
import com.alexhuang.patterndesign.strategy.BlueCouponStrategy;
import com.alexhuang.patterndesign.strategy.ICouponStrategy;
import com.alexhuang.patterndesign.strategy.IMemberStrategy;
import com.alexhuang.patterndesign.strategy.UserPriceModule;

public class TestStrategy {

	public static void main(String[] args) {

		IMemberStrategy strategy1 = new AdvanceMemberStrategy();
		ICouponStrategy strategy2 = new BlueCouponStrategy();
		
		UserPriceModule price = new UserPriceModule(strategy1, strategy2);
        
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
	
	}

}
