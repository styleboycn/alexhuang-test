package com.alexhuang.sort;

import java.util.Arrays;

public class quick_sort {

	static void quicksort(int n[], int left, int right) {
		int dp;
		if (left < right) {
			dp = partition(n, left, right);
			quicksort(n, left, dp - 1);
			quicksort(n, dp + 1, right);
		}
	}
	
	static int partition(int n[], int left, int right) {
		int pivot = n[left];
		while (left < right) {
			while (left < right && n[right] >= pivot)
				right--;
			if (left < right)
				n[left++] = n[right];
			while (left < right && n[left] <= pivot)
				left++;
			if (left < right)
				n[right--] = n[left];
		}
		n[left] = pivot;
		return left;
	}


	public static void main(String[] args) {

		int a[] = { 88, 1, 54, 6, 3, 78, 34, 12, 45 };
		quicksort(a, 0, 8);
		System.out.println(Arrays.toString(a));

	}
}
