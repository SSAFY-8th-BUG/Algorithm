import java.io.*;
import java.util.*;

public class BOJ_백도어_tableMinPark {

    static int N, M, answer;
    static boolean[] w;
    static List<List<Node>> graph;
    static class Node {
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

        answer = solve();
        

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        br.close();        
    }

    static int solve() {
        PriorityQueue<Node> q = new PriorityQueue<>((Node n1, Node n2) -> n1.d - n2.d);
        boolean[] v = new boolean[N];
        int[] value = new int[N];
        Arrays.fill(value, Integer.MAX_VALUE);

        q.add(new Node(0, 0));
        value[0] = 0;

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