package com.alexhuang.patterndesign.template;

public class Test1 {

	public static void main(String[] args) {
		
		GoodStudySwimming goodStudySwimming = new GoodStudySwimming();
		goodStudySwimming.bestPractice();
		
		System.out.println("");
		System.out.println("###########################");
		System.out.println("");
		
		BadStudySwimming badStudySwimming = new BadStudySwimming();
		badStudySwimming.bestPractice();
		
	}

}
