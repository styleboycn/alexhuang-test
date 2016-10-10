package com.alexhuang.patterndesign.decorator;

public class RequirementExt extends RequirementDecorator {

	public RequirementExt(IRequirement basicRequest) {
		super(basicRequest);
		// TODO Auto-generated constructor stub
	}
	
	public void developRequirement() {
		System.out.println("--! before RequirementExt -> developRequirement！");
		super.developRequirement();
		this.addedOperation();
		System.out.println("--! after RequirementExt -> developRequirement！");	
	}
	
	//增强功能
	private void addedOperation() {
		System.out.println("do addedOperation!");
	}
 }  