import java.util.*;
import java.io.*;


public class ST_안전운전을도와줄차세대지능형교통시스템_tableMinPark{
{
    static int N, T, answer;
    static int[][][] map;
    static int[][][] tl = {
            {{0, 0}},
            {{0, 1}, {0, 1}, {1, 0}, {-1, 0}},
            {{-1, 0}, {0, 1}, {0, -1}, {-1, 0}},
            {{0, -1}, {0, -1}, {1, 0}, {-1, 0}},
            {{1, 0}, {0, 1}, {0, -1}, {1, 0}},
            {{0, 1}, {0, 1}, {-1, 0}},
            {{-1, 0}, {0, -1}, {-1, 0}},
            {{0, -1}, {0, -1}, {1, 0}},
            {{1, 0}, {0, 1}, {1, 0}},
            {{0, 1}, {0, 1}, {1, 0}},
            {{-1, 0}, {0, 1}, {-1, 0}},
            {{0, -1}, {0, -1}, {-1, 0}},
            {{1, 0}, {0, -1}, {1, 0}}
    };

    static class P {
        int y;
        int x;
        int t;
        int[] h;
        public P(int y, int x, int[] h, int t){
            this.y = y;
            this.x = x;
            this.h = h;
            this.t = t;
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][N][4];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        answer = 0;
        BFS();

        System.out.println(answer);
        br.close();
    }

    static void BFS(){
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];

        q.add(new P(0, 0, new int[]{-1, 0}, 0));
        v[0][0] = true;

        while(!q.isEmpty()){
            P p = q.poll();
            
            if (p.t == T) break;

            int ti = map[p.y][p.x][p.t % 4];
            if (p.h[0] != tl[ti][0][0] || p.h[1] != tl[ti][0][1]) continue;

            for (int i = 1; i < tl[ti].length; i++){
                int ny = p.y + tl[ti][i][0];
                int nx = p.x + tl[ti][i][1];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

                q.add(new P(ny, nx, tl[ti][i], p.t + 1));
                v[ny][nx] = true;
            }
        }
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (v[i][j]) answer++;
            }
        }
    }
}