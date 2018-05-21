package p15684;

import java.util.Scanner;

public class Main {
	static boolean[][] bridge;
	static int N, M, H;
	
	public static boolean isDone() {
		int count = 0;
		
		for(int i = 1; i <= N; i++) {
			int cur = i;
			
			for(int j = 1; j <= H; j++) {
				// 오른쪽 이동
				int next = cur;
				if(cur < N && bridge[j][cur]) {
					next = cur + 1;
				}
				
				// 왼쪽 이동
				if(cur >= 2 && bridge[j][cur - 1]) {
					next = cur - 1;
				}
				
				cur = next;
			}

			// 도착 했는가
			if(cur == i)
				count++;
		}

		return count == N;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		N = scan.nextInt(); // 세로줄 
		M = scan.nextInt(); // 가로줄
		H = scan.nextInt(); // 가로줄 후보군
		
		/* Brute-force?
		 * 1. 돌아오는 조건을 공식으로 표현하기 어려움
		 * 2. input의 숫자가 작음, 제한시간 2초
		 * => HC0, HC1, HC2, HC3을 구하는 것 */
		
		// 1번부터 세기위해서 H + 1, N + 1, H번째에서 N ~ N+1 의 연결여부
		bridge = new boolean[H + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			int a = scan.nextInt(); // H 인덱스
			int b = scan.nextInt(); // 출발 세로선
			bridge[a][b] = true;
		}
		
		int count = 4;
		for(int i = 0; i < 4; i++) {
			count = solve(0, 0, i);
			if(count < 4) {
				break;
			}
		}
		
		System.out.println(count > 3 ? -1 : count);
		
		scan.close();
	}
	
	public static int solve(int begin, int count, int max) {
		// 현재 상태가 기저인가 검사
		if(count > max) {
			return 4;
		}
		
		// 완성되었는가
		if(isDone()) {
			return count;
		}
		
		// 하나 가로줄 추가해야한다, 재귀로 콤비네이션
		int result = 4;
		for(int i = begin; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				if(bridge[i][j] == false) {
					bridge[i][j] = true;
					result = Math.min(result, solve(i, count + 1, max));
					if(result < 4) {
						return result;
					}
					bridge[i][j] = false;	
				}
			}
		}
		
		return result;
	}

}

