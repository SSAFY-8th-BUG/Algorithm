import java.io.*;
import java.util.*;

/*

이분탐색으로 0 / 10_000 사이의 값들을 넣어보면서 bfs

현재 자리부터 목적지자리까지 매번 계산하면서 급유횟수가 k 보다 작고 거리가 mid보다 작을 때까지 bfs 반복

 */
public class boj_2585_tableMinPark {

    static int n, k, answer;
    static P[] share;

    static class P{
        int y;
        int x;
        public P(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        share = new P[n + 2];

        share[0] = new P(0, 0);
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            share[i] = new P(y, x);
        }
        share[n + 1] = new P(10000, 10000);

        int l = 0;
        int r = 10_000;
        answer = 0;
        while(l <= r){
            int m = (l + r) / 2;

            if (!bfs(m)) l = m + 1;
            else {
                r = m - 1;
                answer = m;
            }
        }
        
        System.out.println(answer);
        br.close();
    }

    static class Node{
        int idx;
        int d;
        public Node(int idx, int d){
            this.idx = idx;
            this.d = d;
        }
    }

    static boolean bfs(int mid){
        Queue<Node> q = new ArrayDeque<>();
        boolean[] v = new boolean[n];

        q.add(new Node(0, 0));
        v[0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();
            P nowP = share[now.idx];

            // 현재 자리에서 목적지까지의 거리가 mid 보다 작은지 확인
            double dt = Math.sqrt(Math.pow(nowP.y - share[n + 1].y, 2) + Math.pow(nowP.x - share[n + 1].x, 2));
            int dist = (int)Math.ceil(dt / 10.0);
            if (dist <= mid) return true;       // 목적지까지의 거리가 mid 보다 작거나 같을경우
            if (now.d > k) continue;            // 방문한 경유지의 수가 k보다 클경우 패스

            // 현재자리에서 목적지까지의 거리가 mid 보다 크기 때문에 다음 경로를 탐색
            for (int i = 0; i < n; i++){
                P nextP = share[i];
                double d = Math.sqrt(Math.pow(nowP.y - nextP.y, 2) + Math.pow(nowP.x - nextP.x, 2));
                int dis = (int)Math.ceil(d / 10.0);

                // 현재 자리와 다음 자리와의 거리가 mid 보다 작고 방문하지 않았을 때
                if (!v[i] && dis <= mid) {
                    q.add(new Node(i, now.d + 1));      // 다음 자리 이동
                    v[i] = true;
                }
            }
        }
        return false;
    }
}
