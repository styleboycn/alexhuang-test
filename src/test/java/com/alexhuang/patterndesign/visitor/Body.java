package com.alexhuang.patterndesign.visitor;

public class Body {
	public void accept(IVisitor visitor) {  
        visitor.visit(this);  
    }
}
