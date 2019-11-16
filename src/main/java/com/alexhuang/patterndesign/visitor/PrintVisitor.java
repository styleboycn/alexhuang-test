package com.alexhuang.patterndesign.visitor;

public class PrintVisitor implements IVisitor {

	public void visit(Wheel wheel) {
		System.out.println("Visiting Wheel		: " + wheel.getWheelName());
	}

	public void visit(Engine engine) {
		System.out.println("Visiting Engine		: " + engine.getEngineName());
	}

	public void visit(Body body) {
		System.out.println("Visiting Body		: " + body.getBodyName());
	}

	public void visit(Car car) {
		System.out.println("Visiting Car		: " + car.getCarName());
	}

}
