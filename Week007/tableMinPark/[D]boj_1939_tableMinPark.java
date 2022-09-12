import java.io.*;
import java.util.*;

// 중량제한
public class boj_1939_tableMinPark {
    static int N, M, S, E, node;
    static int left, right;
    static List<List<Node>> graph;
    static boolean[] v;
    static class Node{
        int n;
        int d;
        public Node(int n, int d){
            this.n = n;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        left = 0;
        right = 0;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
            right = Math.max(right, C);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        while(left <= right){
            int mid = (left + right) / 2;
            node = -1;

            v = new boolean[N + 1];
            dfs(S, mid);

            if (node != -1) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
        br.close();
    }

    static void dfs(int now, int limit){
        if (now == E){
            node = now;
            return;
        }

        v[now] = true;
        for (Node next : graph.get(now)){
            if (!v[next.n] && limit <= next.d) dfs(next.n, limit);
        }
    }
}