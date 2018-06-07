package com.alexhuang.patterndesign.visitor;

public class Engine {
	
	private String engineName;
	
	public Engine(String name) {  
        this.engineName = name;  
    } 
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}
}
