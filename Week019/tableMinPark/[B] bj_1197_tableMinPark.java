import java.io.*;
import java.util.*;

// MST
public class bj_1197_tableMinPark {

    static int V, E, count;
    static long answer;
    static int[] parent;
    static PriorityQueue<Vertex> vertex;
    static class Vertex {
        int s;
        int e;
        long v;
        public Vertex(int s, int e, long v){
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertex = new PriorityQueue<>((Vertex v1, Vertex v2) -> (int)(v1.v - v2.v));

        for (int y = 0; y < E; y++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            long v = Long.parseLong(st.nextToken());
            vertex.add(new Vertex(s, e, v));
        }

        // 서로소집합 초기화
        parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        while(!vertex.isEmpty()) {
            if (count == V - 1) break;

            Vertex now = vertex.poll();

            // 싸이클이 없으면
            if (union(now.s, now.e)) {
                answer += now.v;
                count++;
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        
        if (a > b) parent[a] = b;
        else parent[b] = a;
        return true;
    }
}