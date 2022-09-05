import java.io.*;
import java.util.*;

public class boj_1389_tableMinPark{

    static int N, M, answer, min;
    static List<List<Integer>> graph;
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

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            graph.get(B).add(A);
        }
        answer = -1;
        min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++){
                if (i == j) continue;
                sum += bfs(i, j);
            }
            if (sum < min){
                answer = i;
                min = sum;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int bfs(int n, int dst){
        Queue<Node> q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];

        q.add(new Node(n, 0));
        v[n] = true;

        int dist = 0;

        while (!q.isEmpty()){
            Node now = q.poll();

            if (now.n == dst) {
                dist = now.d;
                break;
            }

            for (int next : graph.get(now.n)){
                if (v[next]) continue;
                q.add(new Node(next, now.d + 1));
                v[next] = true; 
            }
        }
        return dist;
    }
}