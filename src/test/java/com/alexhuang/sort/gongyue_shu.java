package com.alexhuang.sort;

public class gongyue_shu {

	public static int gongyue(int m, int n) {
		while (m % n != 0) {
			int temp = m % n;
			m = n;
			n = temp;
		}
		System.out.println(n);
		return n;
	}

	public static void main(String[] args) {

		gongyue(8, 3);

	}
}
