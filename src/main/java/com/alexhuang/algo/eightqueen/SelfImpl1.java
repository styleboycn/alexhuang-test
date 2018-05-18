package com.alexhuang.algo.eightqueen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelfImpl1 {

	public static void main(String[] args) {
		System.out.println("11");
//		testJiecheng();
		testEightQueen();
	}
	
	public static void testJiecheng() {
        int jiechengRes = jiecheng(5);
        System.out.println("jiechengRes : " + jiechengRes);
	}
	
	public static int jiecheng(int numValue) {
		if (numValue == 1) {
			return 1;
		} else {
			return numValue * jiecheng(numValue -1);
		}
	}
	
	public static final int X_MAX = 8;
	public static final int Y_MAX = 8;
	public static final int PATTERN_LENGTH_MAX = 8;
	
	
	public static void testEightQueen() {
		int[][] patternArray = new int [X_MAX][Y_MAX];
//		patternArray[0][0] = 1;
		eightQueen(0, 0, 0, patternArray);
//        System.out.println("testEightQueen : " + jiechengRes);
	}
	

	public static boolean eightQueen(int x, int y, int patternLen, int[][] patternArray) {
		if (x == X_MAX || y == Y_MAX) {
			return true;
		}
		if (patternLen == PATTERN_LENGTH_MAX) {
			printPattern(patternArray);
			return true;
		}
		
		patternArray[x][y] = 1;
		if (checkValid(patternArray, x, y)) {
			if (eightQueen(x + 1, 0, patternLen + 1, patternArray)) {
				patternArray[x][y] = 0;
				eightQueen(x, y + 1, patternLen, patternArray);
			}
		} else {
			patternArray[x][y] = 0;
			if (eightQueen(x, y + 1, patternLen, patternArray)) {
				//eightQueen(x + 1, y + 1, patternLen, patternArray);
			}
		}
		return true;
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
	
	public static void printPattern(int[][] patternArray) {
		List<String> result = new ArrayList<>();
		for (int x = 0; x < X_MAX; x++) {
			for (int y = 0; y < Y_MAX; y++) {
				if (patternArray[x][y] == 1) {
					result.add(x + "," + y);
				}
			}
		}
		System.out.println("one patternArray : " + Arrays.toString(result.toArray()));
	}

}
