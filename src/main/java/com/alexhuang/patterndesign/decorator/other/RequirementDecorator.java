package com.alexhuang.patterndesign.decorator.other;

public class RequirementDecorator implements IRequirement {
	
	private IRequirement requireInfHolder;
	
	public RequirementDecorator(IRequirement baicRequest) {
		this.requireInfHolder = baicRequest;
	}
	
	public void developRequirement() {
		requireInfHolder.developRequirement();
	}

}
