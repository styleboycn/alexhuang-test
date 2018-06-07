package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.composite.Component;
import com.alexhuang.patterndesign.composite.Composite;
import com.alexhuang.patterndesign.composite.Leaf;

/**
 * 	此模式叫“组合模式”，也可以叫“合成模式”
	此模式包含三个部分
	Component：组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。声明一个接口用于访问和管理Component子部件。
	Leaf：在组合中表示叶子结点对象，叶子结点没有子结点。
	Composite：定义有枝节点行为，用来存储子部件，在Component接口中实现与子部件有关操作，如增加和删除
	《JAVA与模式》之组合模式
	http://www.cnblogs.com/draem0507/p/3794844.html
	《JAVA与模式》之合成模式
	http://www.cnblogs.com/java-my-life/archive/2012/04/17/2453861.html
 */
public class CompositeTest {

	public static void main(String[] args) {
		Component component = new Composite("根节点");
		Component child = new Composite("一级子节点child");
		Component child_1 = new Leaf("一级子节点child之子节点一");
		Component child_2 = new Leaf("一级子节点child之子节点二");
		child.add(child_1);
		child.add(child_2);
		Component child2 = new Composite("一级子节点child2");
		component.add(child);
		component.add(child2);
		component.foreach();
	}

}
