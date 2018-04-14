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
	
	//下面三个方法要定义为protected方法，不对外（包外）暴露
	protected abstract void studyTheoryBook();
	protected abstract void warmupBody();
	protected abstract void morePractice();
	
}
