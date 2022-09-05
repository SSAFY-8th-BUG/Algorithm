import java.io.*;
import java.util.*;

public class boj_14502_tableMinPark{

    static int N, M, answer;
    static int[][] map;
    static boolean[][] v;
    static List<P> virus;
    
    static class P{
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        virus = new ArrayList<>();
        map = new int[N][M];
        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 2) virus.add(new P(y, x));
            }
        }

        answer = 0;
        dfs(0);

        System.out.println(answer);
        br.close();
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static void dfs(int d){
        if (d == 3){
            bfs();
            answer = Math.max(answer, check());
            return;
        }
        
        for (int ny = 0; ny < N; ny++){
            for (int nx = 0; nx < M; nx++){
                if (map[ny][nx] != 0) continue;
                map[ny][nx] = 1;
                dfs(d + 1);
                map[ny][nx] = 0;
            }
        }
    }

    static void bfs(){
        Queue<P> q = new ArrayDeque<>();
        v = new boolean[N][M];

        for (P vir : virus){
            q.add(new P(vir.y, vir.x));
            v[vir.y][vir.x] = true;
        }

        while(!q.isEmpty()){      
            P now = q.poll();
            
            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX]) continue;
                if (map[nextY][nextX] != 0) continue;

                q.add(new P(nextY, nextX));
                v[nextY][nextX] = true;
            }
        }
    }

    static int check(){
        int count = 0;
        for (int y = 0; y < N; y++){
            for (int x = 0; x < M; x++){
                if (map[y][x] == 0 && !v[y][x]) count++;
            }
        }
        return count;
    }

}