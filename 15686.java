import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int ans = Integer.MAX_VALUE; // 치킨집 최대 개수
    static List<place> placeList; // 장소에 대한 리스트
    static List<place> userList; // 사용자에 대한 리스트
    static List<place> selectedPlaceList; // 선택된 값을 저장하는 리스트
    static int N,M; // 문제에 나와있는대로 N은 도시의 정보에 대한 변수, M은 치킨집 개수
    static class place{ // 장소 좌표로 거리 계산을 위해 생성자 선언
        int first,second;

        public place(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }


    private static void dfs(int startPosition, int cnt) { // DFS 알고리즘에 대한 함수, xCm의 원리로 사용자와 치킨집 사이의 거리를 계산
        if (cnt == M){  
            calc();
            return ;  
        }

        for (int i = startPosition+1; i < placeList.size(); i++) { // 시작점이 1부터 시작되므로 +1을 하고 장소에 리스트만큼 for문을 돌린다.
            place p = placeList.get(i);
            selectedPlaceList.add(p);
            dfs(i,cnt+1);
            selectedPlaceList.remove(p);
        }
    }

    private static void calc() {  // 거리를 절대값으로 계산해서 가장 최소거리를 구한다
        int temp=0;
        for(place app : userList){
            int value = Integer.MAX_VALUE;
            for (place p : selectedPlaceList){
                value = Math.min(value, Math.abs(app.first - p.first) + Math.abs(app.second - p.second));
            }
            temp+=value;
        }
        ans = Math.min(temp,ans);
    }


    public static void main(String[] args) throws IOException { // main에서는 입력, 출력값만 나타내줬습니다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        placeList = new ArrayList<>();
        userList = new ArrayList<>();
        selectedPlaceList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <N ; j++) {
                int V = Integer.parseInt(st.nextToken());
                if (V == 1)
                    userList.add(new place(i,j));
                else if (V==2)
                    placeList.add(new place(i,j));
            }
        }
        int cnt=0;

        for (int i = 0; i <placeList.size() ; i++) {
            place p = placeList.get(i);
            selectedPlaceList.add(p);
            dfs(i,cnt+1);
            selectedPlaceList.remove(p);
        }
        System.out.println(ans);
    }
}
