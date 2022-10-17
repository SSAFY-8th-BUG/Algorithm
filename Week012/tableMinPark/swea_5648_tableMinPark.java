import java.io.*;
import java.util.*;
 
// 시간초과 --------------------------------------------------------- 아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
public class swea_5648_tableMinPark {
 
    static int T, N, answer;
    static int[][] map;
    static Queue<Node> q;
 
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class Node {
        int y;
        int x;
        int d;
        int e;
        public Node (int y, int x, int d, int e){
            this.y = y;
            this.x = x;
            this.d = d;
            this.e = e;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        T = Integer.parseInt(br.readLine());
 
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
 
            map = new int[4001][4001];
            q = new ArrayDeque<>();
 
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) + 2000;
                int x = Integer.parseInt(st.nextToken()) + 2000;
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                map[y][x] = e;
                q.offer(new Node(y, x, d, e));
            }
             
            answer = 0;
            while (!q.isEmpty()){
                Node now = q.poll();
 
                if (map[now.y][now.x] != now.e) {       // 자신의 값이 아니면 충돌이 나서 누적합이 된경우
                    answer += map[now.y][now.x];
                    map[now.y][now.x] = 0;
                    continue;
                }
 
                int ny = now.y + dy[now.d];
                int nx = now.x + dx[now.d];
 
                if (ny >= 0 && ny <= 4000 && nx >= 0 && nx <= 4000) {
                    if (map[ny][nx] == 0) {                     // 이동한 지점에 값이 0 이면 값을 대입하고 다시 이동준비
                        map[ny][nx] = now.e;
                        q.add(new Node(ny, nx, now.d, now.e));
                    } else {                                    // 이동한 지점에 값이 존재하면 충돌난 것이기 때문에 누적합
                        map[ny][nx] += now.e;
                    }
                }
                map[now.y][now.x] = 0;      // 이동하기 전의 위치 값을 0 으로 변경
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
 
        System.out.println(sb.toString());
        br.close();
    }
}