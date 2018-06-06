package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.strategy.AdvanceMemberStrategy;
import com.alexhuang.patterndesign.strategy.BlueCouponStrategy;
import com.alexhuang.patterndesign.strategy.ICouponStrategy;
import com.alexhuang.patterndesign.strategy.IMemberStrategy;
import com.alexhuang.patterndesign.strategy.UserPriceModule;

/**
 * 策略模式涉及三个角色
	1、环境(Context)角色：持有一个Strategy的引用。
	2、抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
	3、具体策略(ConcreteStrategy)角色：包装了相关的算法或行为。
	《JAVA与模式》之策略模式
	http://www.cnblogs.com/java-my-life/archive/2012/05/10/2491891.html
 */
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
