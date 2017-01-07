package com.alexhuang.sort;

import java.util.Arrays;

public class bubble_sort {

	public static void bubbleSort(int[] as) {
		if (as == null || as.length <= 1) {
			return;
		}
		int temp = 0;
		for (int i = 0; i < as.length; i++) {
			for (int j = i + 1; j < as.length; j++) {
				if (as[i] > as[j]) {
					temp = as[i];
					as[i] = as[j];
					as[j] = temp;
				}
			}
			System.out.println("one loop over!!!, as[i] : " + as[i]);
		}
	}

	public static void main(String[] args) {
		
		int a[] = { 88, 1, 54, 6, 3, 78, 34, 12, 45 };
		bubbleSort(a);
		System.out.println(Arrays.toString(a));
		
	}

}
