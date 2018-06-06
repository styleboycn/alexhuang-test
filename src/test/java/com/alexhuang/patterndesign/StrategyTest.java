package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.strategy.AdvanceMemberStrategy;
import com.alexhuang.patterndesign.strategy.BlueCouponStrategy;
import com.alexhuang.patterndesign.strategy.ICouponStrategy;
import com.alexhuang.patterndesign.strategy.IMemberStrategy;
import com.alexhuang.patterndesign.strategy.UserPriceModule;

public class StrategyTest {

	public static void main(String[] args) {

		//构造会员策略
		IMemberStrategy strategy1 = new AdvanceMemberStrategy();
		//构造优惠劵策略
		ICouponStrategy strategy2 = new BlueCouponStrategy();
		//构造/初始化用户价格模块
		UserPriceModule price = new UserPriceModule(strategy1, strategy2);
		//报价
		double quote = price.quote(300);
		System.out.println("图书的最终价格为：" + quote);

	}

}
