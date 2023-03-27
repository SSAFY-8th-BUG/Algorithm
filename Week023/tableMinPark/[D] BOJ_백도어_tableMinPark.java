import java.io.*;
import java.util.*;

public class BOJ_백도어_tableMinPark {

    static int N, M, answer;
    static boolean[] w;
    static List<List<Node>> graph;
    static class Node implements Comparable<Node>{
        int n;
        long d;
        public Node(int n, long d){
            this.n = n;
            this.d = d;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.d, o.d);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

        w = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) w[i] = true;
            else w[i] = false;            
        }
        w[N - 1] = true;

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, t));
            graph.get(b).add(new Node(a, t));
        }

        long answer = solve();
        

        System.out.println(answer == Long.MAX_VALUE ? -1 : answer);
        br.close();        
    }

    static long solve() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] v = new boolean[N];
        long[] value = new long[N];
        Arrays.fill(value, Long.MAX_VALUE);

        q.add(new Node(0, 0));
        value[0] = 0L;

        while(!q.isEmpty()){
            Node now = q.poll();

            if (v[now.n] || w[now.n] || now.n == N - 1) continue;
            v[now.n] = true;

            for (Node next : graph.get(now.n)) {             
                if (value[next.n] > value[now.n] + next.d){
                    value[next.n] = value[now.n] + next.d;
                    q.add(new Node(next.n, value[next.n]));                    
                }
            }
        }
        return value[N - 1];
    }
}