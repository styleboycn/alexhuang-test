package com.alexhuang.patterndesign.visitor;

public class PrintVisitor implements IVisitor {

	public void visit(Wheel wheel) {
		System.out.println("Visiting " + wheel.getWheelName() + " wheel");  
	}

	public void visit(Engine engine) {
		System.out.println("Visiting " + engine.getEngineName());
	}

	public void visit(Body body) {
		System.out.println("Visiting " + body.getBodyName());
	}

	public void visit(Car car) {
		System.out.println("Visiting " + car.getCarName());
	}

}
