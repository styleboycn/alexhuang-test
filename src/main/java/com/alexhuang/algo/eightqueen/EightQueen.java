package com.alexhuang.algo.eightqueen;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年5月16日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */

//Java实现八皇后问题
//https://blog.csdn.net/H_JinXian/article/details/51088752

public class EightQueen {

	public static void main(String[] args) {
		Empress a = new Empress();
		a.find(0);
		System.out.println("八皇后问题共有：" + a.map + "种可能");
	}
}

class Empress {
	
	public int[][] arry = new int[8][8]; // 棋盘，放皇后
	public int map = 0; // 存储方案结果

	public boolean rule(int arry[][], int k, int j) { // 判断节点是否合适
		for (int i = 0; i < 8; i++) { // 行列冲突
			if (arry[i][j] == 1)
				return false;
		}
		for (int i = k - 1, m = j - 1; i >= 0 && m >= 0; i--, m--) { // 左对角线
			if (arry[i][m] == 1)
				return false;
		}
		for (int i = k - 1, m = j + 1; i >= 0 && m <= 7; i--, m++) { // 右对角线
			if (arry[i][m] == 1)
				return false;
		}
		return true;
	}

	public void find(int i) { // 寻找皇后节点
		if (i > 7) { // 八皇后解
			map++;
			print();
			return;
		}
		for (int m = 0; m < 8; m++) { // 深度优先,递归算法
			if (rule(arry, i, m)) {
				arry[i][m] = 1;
				find(i + 1);
				arry[i][m] = 0;
			}
		}
	}

	public void print() { // 打印方法结果
		System.out.print("方案" + map + ":");
		if (arry[0][0] != 1) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int m = 0; m < 8; m++) {
				if (arry[i][m] == 1) {
					System.out.print("皇后" + (i + 1) + "在第" + i + "行，第" + m
							+ "列\t");
				}
			}
		}
		System.out.println();
	}
}
