package p10026;

import java.util.Scanner;

public class Main {	// 적록색약
	static String[] map;
	static boolean[][] mark;
	static int size;
	static int count;
	static int max;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		size = Integer.parseInt(scan.nextLine());
		map = new String[size];
		max = size * size;
		for(int i = 0; i < size; i++) {
			map[i] = scan.nextLine();
		}
		
		System.out.print(solve() + " ");

		for(int i = 0; i < size; i++) 
			map[i] = map[i].replace('G', 'R');
		
		System.out.println(solve());
		
		scan.close();
	}
	
	public static int solve() {
		// 초기화
		mark = new boolean[size][size];
		count = 0;
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
//				System.out.println("[" + i + " , " + j + "]");
				if(search(i, j)) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static boolean search(int row, int col) { 
//		System.out.println("\t(" + row + " , " + col +") : " + map[row].charAt(col));
		// 재방문
		if(mark[row][col]) {
			return false;
		}

		mark[row][col] = true;
		
		char cur = map[row].charAt(col);
		int nRow, nCol;
		
		// 상
		nRow = row - 1;
		nCol = col;
		if((nRow >= 0) && (map[nRow].charAt(nCol) == cur) && (mark[nRow][nCol] == false)) {
			search(nRow, nCol);
		}
		
		// 우
		nRow = row;
		nCol = col + 1;
		if((nCol < size) && (map[nRow].charAt(nCol) == cur) && (mark[nRow][nCol] == false)) {
			search(nRow, nCol);
		}
		
		// 하
		nRow = row + 1;
		nCol = col;
		if((nRow < size) && (map[nRow].charAt(nCol) == cur) && (mark[nRow][nCol] == false)) {
			search(nRow, nCol);
		}
		
		// 좌
		nRow = row;
		nCol = col - 1;
		if((nCol >= 0) && (map[nRow].charAt(nCol) == cur) && (mark[nRow][nCol] == false)) {
			search(nRow, nCol);
		}
		
		return true;
	}
	
	
}

