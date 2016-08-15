package com.alexhuang.patterndesign.template;

public abstract class AbstractStudySwimming {
	
	//bestPractice() is templateMethod
	public void bestPractice() {
		
		System.out.println("begin swimming best practice");
		
		studyTheoryBook();
		
		warmupBody();
		
		morePractice();
		
		System.out.println("end swimming best practice");
	}
	
	protected abstract void studyTheoryBook();
	
	protected abstract void warmupBody();
	
	protected abstract void morePractice();
	
}
