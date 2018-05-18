package com.alexhuang.algo.eightqueen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelfImpl2 {
	
	public static final int X_MAX = 8;
	public static final int Y_MAX = 8;
	public static final int PATTERN_LENGTH_MAX = 8;
	public static final int[][] patternArray = new int [X_MAX][Y_MAX];
	public static int patternTimes = 0;

	public static void main(String[] args) {
		testEightQueen();
		System.out.println("Total pattern times : " + patternTimes);
	}
	
	public static void testEightQueen() {
		eightQueen(0);
	}

	public static void eightQueen(int x) {
		if (x > 7) {
			patternTimes++;
			printPattern();
			return;
		}
		for (int idx = 0; idx < X_MAX; idx++) {
			if (checkValid(patternArray, x, idx)) {
				patternArray[x][idx] = 1;
				eightQueen(x + 1);
				patternArray[x][idx] = 0;
			}
		}
	}
	
	public static boolean checkValid(int[][] patternArray, int targetX, int targetY) {
		for (int x = 0; x < X_MAX; x++) {
			for (int y = 0; y < Y_MAX; y++) {
				if (patternArray[x][y] == 1) {
					if (!checkRow(patternArray, targetX, targetY)
							|| !checkColumn(patternArray, targetX, targetY)
							|| !checkSlant(patternArray, targetX, targetY)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean checkRow(int[][] patternArray, int targetX, int targetY) {
		for (int x = 0; x < X_MAX; x++) {
			if (x == targetX) {
				continue;
			} 
			if (patternArray[x][targetY] == 1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkColumn(int[][] patternArray, int targetX, int targetY) {
		for (int y = 0; y < Y_MAX; y++) {
			if (y == targetY) {
				continue;
			} 
			if (patternArray[targetX][y] == 1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkSlant(int[][] patternArray, int targetX, int targetY) {
		for (int idx = 1; idx < X_MAX; idx++) {
			//x- & y-
			if ((targetX - idx) >= 0 && (targetY - idx) >= 0) {
				if (patternArray[targetX - idx][targetY - idx] == 1) {
					return false;
				}
			}
			//x+ & y-
			if ((targetX + idx) < X_MAX && (targetY - idx) >= 0) {
				if (patternArray[targetX + idx][targetY - idx] == 1) {
					return false;
				}
			}
			//x+ & y+
			if ((targetX + idx) < X_MAX && (targetY + idx) < Y_MAX) {
				if (patternArray[targetX + idx][targetY + idx] == 1) {
					return false;
				}
			}
			//x- & y+
			if ((targetX - idx) >= 0 && (targetY + idx) < Y_MAX) {
				if (patternArray[targetX - idx][targetY + idx] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void printPattern() {
		List<String> result = new ArrayList<>();
		for (int x = 0; x < X_MAX; x++) {
			for (int y = 0; y < Y_MAX; y++) {
				if (patternArray[x][y] == 1) {
					result.add(x + "," + y);
				}
			}
		}
		System.out.println("patternArray "  + patternTimes + " : " + Arrays.toString(result.toArray()));
	}

}
