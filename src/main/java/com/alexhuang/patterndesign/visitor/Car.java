package com.alexhuang.patterndesign.visitor;

public class Car {
	
	private String carName;

	private Engine engine = new Engine("hongniu engine");
	private Body body = new Body("falali body");
	private Wheel[] wheels = { 
			new Wheel("front left wheel"),
			new Wheel("front right wheel"), 
			new Wheel("back left wheel"),
			new Wheel("back right wheel") 
			};
	
    public Car(String name) {  
        this.carName = name;  
    } 
    
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		engine.accept(visitor);
		body.accept(visitor);
		for (int i = 0; i < wheels.length; ++i)
			wheels[i].accept(visitor);
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
	
}
