package com.alexhuang.patterndesign.visitor;

public class PrintVisitor implements IVisitor {

	public void visit(Wheel wheel) {
		// TODO Auto-generated method stub
		System.out.println("Visiting " + wheel.getName() + " wheel");  
	}

	public void visit(Engine engine) {
		// TODO Auto-generated method stub
		System.out.println("Visiting engine");
	}

	public void visit(Body body) {
		// TODO Auto-generated method stub
		System.out.println("Visiting body");  
	}

	public void visit(Car car) {
		// TODO Auto-generated method stub
		System.out.println("Visiting car"); 
	}


	
}
