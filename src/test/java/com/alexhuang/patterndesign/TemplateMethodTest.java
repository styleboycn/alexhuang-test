package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.template.AbstractStudySwimming;
import com.alexhuang.patterndesign.template.BadStudySwimming;
import com.alexhuang.patterndesign.template.GoodStudySwimming;

//Template Method模式
//http://blog.csdn.net/dadalan/article/details/3868489

//场景：对于某一个业务逻辑（算法实现）在不同的对象中有不同的细节实现，但是逻辑（算法）的框架（或通用的应用算法）是相同的。
//实现方式：Template Method模式是采用继承的方式实现这一点：将逻辑（算法）框架放在抽象基类中，并定义好细节的接口，子类中实现细节。
//原理：Template Method模式实际上就是利用面向对象中多态的概念实现算法实现细节和高层接口的松耦合。

//注意：将原语操作（细节算法）定义为受保护（Protected）成员，对外部只提供模板方法供调用。
//上面的意思是说，将AbstractStudySwimming中studyTheoryBook()、warmupBody()、morePractice()
//定义为protected方法，不对外暴露，只将模板方法bestPractice()对外暴露。

public class TemplateMethodTest {

	public static void main(String[] args) {
		
		AbstractStudySwimming goodStudySwimming = new GoodStudySwimming();
		goodStudySwimming.bestPractice();
		
		System.out.println("");
		
		AbstractStudySwimming badStudySwimming = new BadStudySwimming();
		badStudySwimming.bestPractice();
		
	}

}
