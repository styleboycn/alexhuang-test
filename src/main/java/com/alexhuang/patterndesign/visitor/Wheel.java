package com.alexhuang.patterndesign.visitor;

public class Wheel {
	
	private String wheelName;
	
    public Wheel(String name) {  
        this.wheelName = name;  
    } 

    public  void accept(IVisitor visitor) {  
        visitor.visit(this);  
    }
	public String getWheelName() {
		return wheelName;
	}
	public void setWheelName(String wheelName) {
		this.wheelName = wheelName;
	}
}
