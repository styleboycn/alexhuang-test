package com.alexhuang.aviator;

import java.util.HashMap;  
import java.util.Map;  
  
import com.googlecode.aviator.AviatorEvaluator;  
import com.googlecode.aviator.Expression;
  
/**
* 基于Aviator的规则引擎Demo
* http://shihlei.iteye.com/blog/2421576
* Aviator 表达式求值引擎开源框架
* https://blog.csdn.net/lxzo123/article/details/6082338
*/

public class AviatorDemo {
	
	public static void test1() {
		String expression = "a + b + c";

		Map<String, Object> params = new HashMap<>();
		params.put("a", 1);
		params.put("b", 2);
		params.put("c", 88);

		long result = (long) AviatorEvaluator.execute(expression, params);
		System.out.println("test1() : " + result);
	}
	
	public static void test2() {
		Long result = (Long) AviatorEvaluator.execute("1+2*3"); 
	    System.out.println("test2() : " + result);
	}
	
	public static void test3() {
		String expression = "a-(b-c)>100";
		// 编译表达式
		Expression compiledExp = AviatorEvaluator.compile(expression);

		Map<String, Object> env = new HashMap<String, Object>();
		env.put("a", 100.3);
		env.put("b", 45);
		env.put("c", -199.100);

		// 执行表达式
		Boolean result = (Boolean) compiledExp.execute(env);
		System.out.println("test3() : " + result);
	}

	public static void main(String[] args) {

		test1();
		test2();
		test3();
	}
}

