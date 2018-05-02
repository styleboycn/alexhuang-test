package com.alexhuang.aviator;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;

/**
* 基于Aviator的规则引擎Demo
* http://shihlei.iteye.com/blog/2421576
* Aviator 表达式求值引擎开源框架
* https://blog.csdn.net/lxzo123/article/details/6082338
*/

public class AviatorSelfFunctionDemo {
	
	public static void main(String[] args) {
		// 注册函数
		AviatorEvaluator.addFunction(new MySumFunction());

		String expression = "my_sum(a,b,c)";

		Map<String, Object> params = new HashMap<>();
		params.put("a", 1);
		params.put("b", 2);
		params.put("c", 3);

		long result = (long) AviatorEvaluator.execute(expression, params);

		System.out.printf("result : " + result);
	}
	
	/**
	 * 自定义函数，实现三元数据求和
	 */
	static class MySumFunction extends AbstractFunction {

		@Override
		public AviatorObject call(Map<String, Object> env, AviatorObject a,
				AviatorObject b, AviatorObject c) {
			Number numA = FunctionUtils.getNumberValue(a, env);
			Number numB = FunctionUtils.getNumberValue(b, env);
			Number numC = FunctionUtils.getNumberValue(c, env);

			long result = numA.longValue() + numB.longValue() + numC.longValue();
			return new AviatorLong(result);
		}

		/**
		 * 获取函数名
		 * 
		 * @return 函数名
		 */
		public String getName() {
			return "my_sum";
		}
	} 
}
