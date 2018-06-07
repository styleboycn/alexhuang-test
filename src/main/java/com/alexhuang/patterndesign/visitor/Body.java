package com.alexhuang.patterndesign.visitor;

public class Body {
	
	private String bodyName;
	
    public Body(String name) {  
        this.bodyName = name;  
    } 
	
	public void accept(IVisitor visitor) {  
        visitor.visit(this);  
    }

	public String getBodyName() {
		return bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}
}
