package p13460;

import java.util.Scanner;

public class Main {
	static int height;
	static int width;
	static int[] dx = { 0, +1, 0, -1 }; // �礷
	static int[] dy = { -1, 0, +1, 0 };
	static Ball goal;
	static int min = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// �Է�
		height = scan.nextInt();
		width = scan.nextInt();
		scan.nextLine();
		
		Ball red = null, blue = null;
		char[][] board = new char[height][width];
		for (int i = 0; i < height; i++) {
			board[i] = scan.nextLine().toCharArray();
			for(int j = 0; j < width; j++) {
				if(board[i][j] == 'R') {
					red = new Ball('R', j, i);
				} else if(board[i][j] == 'B') {
					blue = new Ball('B', j, i);
				} else if(board[i][j] == 'O') {
					goal = new Ball('O', j, i);
				}
			}
		}

		int count = -1;
		for (int maxCount = 1; maxCount <= 10; maxCount++) {
			for (int dir = 0; dir < 4; dir++) {
				count = search(copy(board), dir, maxCount, 0, new Ball(red), new Ball(blue));
				if(count != -1) {
					break;
				}
			}
			
			if(count != -1)
				break;
		}

		System.out.println(count);
		
		scan.close();
	}

	static char[][] copy(char[][] src) {
		char[][] dest = new char[src.length][src[0].length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				dest[i][j] = src[i][j];
			}
		}

		return dest;
	}

	static int search(char[][] board, int direction, int maxCount, int count, Ball red, Ball blue) {
		// ��������
		if (count >= maxCount) {
			return -1;
		}

		// �����δ� R, B�� ���θ��� �ȿ����� �� ������ �ѹ� �������δ�.
		count++;
		red.move(board, direction);
		blue.move(board, direction);
		blue.move(board, direction);
		red.move(board, direction);

		// �ش�ã�����
		if(blue.x == goal.x && blue.y == goal.y) {
			return -1;
		} else if(red.x == goal.x && red.y == goal.y) {
			return count;
		}
		
		int result = -1;
		for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
			if (nextDirection == direction) {
				continue;
			}

			result = search(copy(board), nextDirection, maxCount, count, new Ball(red), new Ball(blue));
			if(result != -1) {
				break;
			}
		}

		return result;
	}
	

	public static class Ball {
		char color;
		int x, y;

		public Ball(Ball ball) {
			this.color = ball.color;
			this.x = ball.x;
			this.y = ball.y;
		}

		public Ball(char color, int x, int y) {
			super();
			this.color = color;
			this.x = x;
			this.y = y;
		}
		
		void move(char[][] board, int direction) {
			while(true) {
				if(this.color == 'O')
					return;
				
				int nx = this.x + dx[direction];
				int ny = this.y + dy[direction];
				boolean move = false;
				
				if(board[ny][nx] == '.') {
					board[ny][nx] = this.color;
					board[this.y][this.x] = '.';
					
					this.x = nx;
					this.y = ny;
					move = true;
				} else if(board[ny][nx] == 'O') {
					this.color = 'O';
					board[this.y][this.x] = '.';
					
					this.x = nx;
					this.y = ny;
				}
				
				if(move == false) {
					return;
				}
			}
		}
	}
}
