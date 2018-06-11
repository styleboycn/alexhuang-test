package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.builder.ConcreteBuilder;
import com.alexhuang.patterndesign.builder.Director;
import com.alexhuang.patterndesign.builder.IBuilder;
import com.alexhuang.patterndesign.builder.Product;

/**
 * 建造模式是对象的创建模式。建造模式可以将一个产品的内部表象（internal representation）与产品的生产过程分割开来，
 * 从而可以使一个建造过程生成具有不同的内部表象的产品对象。
 * 《JAVA与模式》之建造模式
 * http://www.cnblogs.com/java-my-life/archive/2012/04/07/2433939.html
 */
public class BuilderTest {

	public static void main(String[] args) {
		IBuilder builder = new ConcreteBuilder();
		Director director = new Director(builder);
		director.construct();
		Product product = builder.retrieveResult();
		System.out.println(product.getPart1());
		System.out.println(product.getPart2());
	}

}
