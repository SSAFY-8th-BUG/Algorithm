import java.io.*;
import java.util.*;

public class boj_7562_tableMinPark{

    static int T, I, answer;
    static P s, e;

    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static class P{
        int y;
        int x;
        int d;
        public P (int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            I = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            s = new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            st = new StringTokenizer(br.readLine());
            e = new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
            
            answer = Integer.MAX_VALUE;
            bfs();

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static void bfs(){
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[I][I];

        q.add(new P(s.y, s.x, 0));
        v[s.y][s.x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            if (now.y == e.y && now.x == e.x){
                answer = Math.min(answer, now.d);
            }

            for (int i = 0; i < 8; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= I || nextX < 0 || nextX >= I || v[nextY][nextX]) continue;
            
                q.add(new P(nextY, nextX, now.d + 1));
                v[nextY][nextX] = true;
            }
        }
    }
}