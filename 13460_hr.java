/* 문제 접근 -> DFS 알고리즘으로 해결. 
상 - 하, 좌, 우 - ...
하 - 상, 좌, 우 - ...
좌 - 상, 하, 우 - ...
우 - 상, 하, 좌 - ...
1. 파란 공과 빨간 공 중에서 어떤 공이 먼저 움직이는지를 파악하고
- 파란 공이 먼저 움직여야 되는 상황에서는 파란공->빨간공 순서로 움직이고
- 빨간 공이 먼저 움직여야 되는 상황에서는 빨간공->파란공 순서로 움직인다.

2. 맵을 전역 변수로 설정할 경우 맵이 바뀐 상태에서 움직이게 된다.
- 맵을 들고다녀야 한다

3. 이전에 움직인 방향을 또 한번 움직이거나, 바로 반대 방향으로 움직이는건 제외시켜준다. */

import java.util.Scanner;

public class Main {
    static String map[][];
    static int N, M; // N은 보드의 세로 크기, M은 가로 크기
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1}; 
    static int bx, by;
    static int rx, ry;
    static int INF = 0x7fffffff; // 최대 크기 바이트 임의 설정
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new String[N][M];
        
        // 입력
        for(int i = 0; i<N; i++) {
            String s = sc.next(); 
            String[] split = s.split("");
            for (int j=0; j<M; j++) {
                map[i][j] = split[j];
                if(split[j].equals("R")) { // 빨간 구슬 
                    ry = i;
                    rx = j;
                }else if(split[j].equals("B")) { // 파란 구슬
                    by = i;
                    bx = j;
                }
            }
        }
        int ret = go(0); // depth 0으로 시작.
        System.out.println(ret == INF? -1 : ret); // depth 최대면 -1을 리턴
    }
    static int rotate(int dir) {
        boolean bo = false, ro = false;
        boolean rstop = false, bstop = false; // rstop은 빨간 구슬이 멈춘 상태 즉 파란 구슬이 움직일 때, bstop은 파란 구슬이 멈춘 상태 즉 빨간 구슬이 움직일 때
        int nrx = 0, nry = 0;
        int nbx = 0, nby = 0;
        boolean priority = chkPriority(dir); // 우선순위 값 
        
        while(!rstop || !bstop) {
            // 빨간 구슬이 움직일 때
            if (!rstop) {
                nry = ry + dy[dir];
                if(nrx < 0 || nry < 0 || nrx >= M || map[nry][nrx].equals("#"))
                    rstop = true;
                else if(map[nry][nrx].equals("0")) {
                    ro = true;
                    rstop = true;
                } else {
                    ry = nry;
                    rx = nrx;
                }           
            }
            // 파란 구슬이 움직일 때
            if(!bstop) {
                nby = by + dy[dir];
                nbx = bx + dx[dir];
                if(nbx < 0 || nby < 0 || nby >= N || nbx >= M || map[nby][nbx].equals("#"))
                    bstop = true;
                else if(map[nby][nbx].equals("0")) {
                    bo = true;
                    bstop = true;
                } else {
                    by = nby;
                    bx = nbx;
                }
            }
        }
        
        // 공 위치 같을 때 재배치
        if(rx == bx && ry == by) {
            if(dir == 0) {
                if(priority)
                    by++;
                else
                    ry++;
            }
            else if (dir==1) {
                if(priority)
                    by--;
                else
                    ry--;
            }
            else if (dir == 2) {
                if(priority)
                    bx++;
                else
                    rx++;
            }
            else {
                if(priority)
                    bx--;
                else
                    rx--;
            }
        }
        // 파란 구슬이 나오는 경우가 있으면 무시.
        if(bo == true)
            return 0;
        else if(ro == true)
            return 1;
        else
            return 2;
    }
    
    static boolean chkPriority(int d) { // 빨간 구슬이 높으면 true 아니면 false
        switch(d) {
            case 0:
                if(ry < by)
                    return true;
                else
                    return false;
            case 1:
                if(ry > by)
                    return true;
                else
                    return false;
            case 2:
                if(rx < bx)
                    return true;
                else
                    return false;
            case 3:
                if(rx > bx)
                    return true;
                else
                    return false;
            default:
               return false;    
        }
    }
    static int go(int depth) {
        if(depth >= 10)
            return INF;
        
        int brx = rx;
        int bry = ry;
        int bbx = bx;
        int bby = by;
        int ret = INF;
        
        // 상하좌우 이동
        for(int i=0; i<4; i++) {
            int tmp = rotate(i);
            
            if(tmp == 1)
                return depth+1;
            else if(tmp == 2){
                int tmp2 = go(depth+1);
                if(ret > tmp2)
                    ret = tmp2;
            }
                rx = brx;
                ry = bry;
                bx = bbx;
                by = bby;
        }
        return ret;
    }
}
