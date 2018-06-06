package p12100;

import java.util.Scanner;

public class Main {
	static int size = 0;
	static int max = 0;
	static final int EMPTY = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		size = scan.nextInt();
		int[][] board = new int[size + 1][size + 1];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = scan.nextInt();
				max = Math.max(board[i][j], max);
			}
		}

//		left(board);
//		print(board);
		
		solve(0, copy(board));
		System.out.println(max);

		scan.close();
	}

	static void solve(int count, int[][] board) {
		if (count >= 5) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[][] tmp = copy(board);
			move(i, tmp);
			solve(count + 1, tmp);
		}
	}

	static void left(int[][] board) {
		boolean[][] check = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size; j++) {
				int cur = board[i][j];
				if (cur == EMPTY)
					continue;

				// 1. 앞에 0이 아닌 자리를 찾아 움직인다
				int next = j - 1;
				while ((next >= 0) && board[i][next] == EMPTY) {
					next--;
				}

				board[i][j] = 0;
				board[i][next + 1] = cur;

				if ((next >= 0) && board[i][next] == cur && check[i][next] == false) {
					board[i][next + 1] = 0;
					board[i][next] += cur;
					check[i][next] = true;

					max = Math.max(board[i][next], max);
				}
			}
		}
	}

	static void right(int[][] board) {
		boolean[][] check = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = size - 2; j >= 0; j--) {
				int cur = board[i][j];
				if (cur == EMPTY)
					continue;

				// 1. 앞에 0이 아닌 자리를 찾아 움직인다
				int next = j + 1;
				while (next <= size - 1 && board[i][next] == EMPTY) {
					next++;
				}

				board[i][j] = 0;
				board[i][next - 1] = cur;

				if ((next <= (size - 1)) && board[i][next] == cur && check[i][next] == false) {
					board[i][next - 1] = 0;
					board[i][next] += cur;
					check[i][next] = true;

					max = Math.max(board[i][next], max);
				}
			}
		}
	}

	static void up(int[][] board) {
		boolean[][] check = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size; j++) {
				int cur = board[j][i];
				if (cur == EMPTY)
					continue;

				// 1. 앞에 0이 아닌 자리를 찾아 움직인다
				int next = j - 1;
				while ((next >= 0) && board[next][i] == EMPTY) {
					next--;
				}

				board[j][i] = 0;
				board[next + 1][i] = cur;

				if ((next >= 0) && board[next][i] == cur && check[next][i] == false) {
					board[next + 1][i] = 0;
					board[next][i] += cur;
					check[next][i] = true;

					max = Math.max(board[next][i], max);
				}
			}
		}
	}

	static void down(int[][] board) {
		boolean[][] check = new boolean[size][size];
		for (int i = size - 1; i >= 0; i--) {
			for (int j = size - 2; j >= 0; j--) {
				int cur = board[j][i];
				if (cur == EMPTY)
					continue;

				// 1. 앞에 0이 아닌 자리를 찾아 움직인다
				int next = j + 1;
				while (next <= size - 1 && board[next][i] == EMPTY) {
					next++;
				}

				board[j][i] = 0;
				board[next - 1][i] = cur;

				if ((next <= (size - 1)) && board[next][i] == cur && check[next][i] == false) {
					board[next - 1][i] = 0;
					board[next][i] += cur;
					check[next][i] = true;

					max = Math.max(board[next][i], max);
				}
			}
		}
	}

	static void print(int[][] board) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i][j] + " ");
			}

			System.out.println();
		}
	}

	static void move(int dir, int[][] board) {
		switch (dir) {
		case 0:
			left(board);
			break;

		case 1:
			right(board);
			break;

		case 2:
			up(board);
			break;

		case 3:
			down(board);
			break;

		default:
			break;
		}
	}

	public static int[][] copy(int[][] source) {
		int[][] dest = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				dest[i][j] = source[i][j];
			}
		}

		return dest;
	}

}
