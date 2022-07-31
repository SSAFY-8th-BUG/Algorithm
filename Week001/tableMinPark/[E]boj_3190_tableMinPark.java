import java.io.*;
import java.util.*;

public class boj_3190_tableMinPark {

    public static class P{
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static int[] py = {0, 1, 0, -1};
    static int[] px = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] v = new boolean[N][N];

        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken()) - 1;
            int X = Integer.parseInt(st.nextToken()) - 1;
            map[Y][X] = 1;
        }
        int L = Integer.parseInt(br.readLine());

        HashMap<Integer, Character> dir = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            dir.put(X, C);
        }
        
        int answer = 0;
                
        Queue<P> q = new LinkedList<>();
        q.add(new P(0, 0));
        v[0][0] = true;

        int nextY = 0;
        int nextX = 0;
        int d = 0;

        while(true){
            nextY = nextY + py[d];
            nextX = nextX + px[d];
            answer++;
            
            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) break;
            if (v[nextY][nextX]) break;
            
            q.add(new P(nextY, nextX));
            v[nextY][nextX] = true;

            if (map[nextY][nextX] == 0){
                P tail = q.poll();
                v[tail.y][tail.x] = false;
            } else {
                map[nextY][nextX] = 0;
            }

            // 방향전환 (원형배열 형태로 풀다가 2시간 허비함 -> 음수인 경우도 있어서 순서가 다르게 돌아간다.)
            if (dir.containsKey(answer)){
                if (dir.get(answer) == 'L') d--;
                else d++;

                if (d < 0) d = 3;
                else if (d > 3) d = 0;
            }

        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.flush();
        bw.close();
    }
}
