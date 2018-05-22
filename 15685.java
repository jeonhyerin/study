package p15685;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static boolean[][] map = new boolean[101][101];
	static ArrayList<DragonCurve> curves = new ArrayList<DragonCurve>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// �Է�
		int cases = scan.nextInt();
		while (cases-- > 0) {
			curves.add(new DragonCurve(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
		}

		// Ŀ�� �׸���
		for (int i = 0; i < curves.size(); i++) {
			drawCurves(curves.get(i));
		}

		// �簢�� ����
		System.out.println(count());

		scan.close();
	}

	public static void drawCurves(DragonCurve curve) {
		ArrayList<Integer> edges = new ArrayList<Integer>();
		int x = curve.x;
		int y = curve.y;
		int dir = curve.dir;
		
		// 1. 0���� �巡�� Ŀ�� 
		map[y][x] = true;
		x += dx[dir];
		y += dy[dir];
		map[y][x] = true;
		
		edges.add(dir);
		
		// 2. �巡��Ŀ�� ����(x, y)���� �巡�� Ŀ�긦 �Ųٷ� ���󰡸� �ݽð�������� 90�� ���� ���δ�
		while(curve.gen-- > 0) {
			int max = edges.size() - 1;
			for(int i = max; i >= 0; i--) {
				dir = (edges.get(i) + 1) % 4;
				
				// �� �׸���
				map[y][x] = true;
				x += dx[dir];
				y += dy[dir];
				map[y][x] = true;
				
				// ���� ������ 
				edges.add(dir);
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j]) {
					System.out.print("o ");
				} else {
					System.out.print("x ");
				}
			}
			System.out.println();
		}
	}

	public static int count() {
		int count = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					count++;
				}
			}
		}

		return count;
	}

}

class DragonCurve {
	int x;
	int y;
	int dir;
	int gen;

	public DragonCurve(int x, int y, int dir, int gen) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gen = gen;
	}

}