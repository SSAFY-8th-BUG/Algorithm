import java.io.*;
import java.util.*;

// 행성 T
// N 개의 행성 간에 플로우 설치
// 플로우 = 순간이동
// 플로우 관리 비용 C
// MST
public class bj_16398_tableMinPark {

    static int N, count;
    static long answer;
    static int[] parent;
    static PriorityQueue<Vertex> ver;
    static class Vertex {
        int s;
        int e;
        int v;
        public Vertex(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ver = new PriorityQueue<>((Vertex v1, Vertex v2) -> v1.v - v2.v);

        StringTokenizer st;

        for (int s = 0; s< N; s++) {
            st = new StringTokenizer(br.readLine());
            for (int e= 0; e < N; e++) {
                int v = Integer.parseInt(st.nextToken());
                if (s >= e) continue;
                ver.add(new Vertex(s, e, v));
            }
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        while(!ver.isEmpty()) {
            if (count == N - 1) break;

            Vertex now = ver.poll();

            // 싸이클 확인
            if (union(now.s, now.e)) {
                count++;
                answer += now.v;
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