import java.io.*;
import java.util.*;

// 연구소2
public class boj_17141_tableMinPark {

    static int N, V, answer;
    static int[][] map;
    static int[] tgt;
    static List<P> virus;
    static Queue<P> q;
    static boolean[][] v;

    static class P{
        int y;
        int x;
        int d;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
        public P(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();
        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 2){
                    map[y][x] = 0; 
                    virus.add(new P(y, x));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        tgt = new int[V];
        comb(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        br.close();
    }

    static void comb(int tgtIdx, int startIdx){
        if (tgtIdx == V){
            q = new ArrayDeque<>();
            v = new boolean[N][N];

            for (int i = 0; i < tgtIdx; i++){
                P temp = virus.get(tgt[i]);
                q.add(new P(temp.y, temp.x, 0));
                v[temp.y][temp.x] = true;
            }
            answer = Math.min(answer, bfs());            
            return;
        }

        for (int next = startIdx; next < virus.size(); next++){
            tgt[tgtIdx] = next;
            comb(tgtIdx + 1, next + 1);
        }
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int bfs(){
        int time = 0;

        while(!q.isEmpty()){
            P now = q.poll();
            
            time = Math.max(time, now.d);

            for (int i = 0; i < 4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx] || map[ny][nx] == 1) continue;

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }

        for (int y = 0; y < N; y++){
            for (int x = 0; x < N; x++){
                if (map[y][x] == 0 && !v[y][x]) return Integer.MAX_VALUE;
            }
        }
        return time;
    }
}
