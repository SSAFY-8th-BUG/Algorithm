import java.io.*;
import java.util.*;

public class bj_1414_tableMinPark {
    static int N, answer;
    static PriorityQueue<Vertex> v;
    static int[] parents;

    static class Vertex{
        int from;
        int to;
        int cost;
        public Vertex(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        v = new PriorityQueue<>((Vertex v1, Vertex v2) -> v1.cost - v2.cost);
        
        answer = 0;
        for (int i = 0; i < N; i++) {
            char[] alps = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                int cost = number(alps[j]);
                if (cost == 0) continue;
                answer += cost;
                v.add(new Vertex(i, j, cost));
            } 
        }

        makeSet();

        int count = 0;
        while(!v.isEmpty()) {
            Vertex now = v.poll();
            if (union(now.from, now.to)) {
                answer -= now.cost;
                count++;
            }
            if (count == N - 1) break;
        }

        // 모두 연결되어있는지 확인
        int firstParent = find(0);
        for (int i = 1; i < N; i++) {
            if (firstParent != find(i)) {
                answer = -1;
                break;
            }
        }

        System.out.println(answer);
        br.close();
    }

    // MST 크루스칼
    // 가중치 기준으로 간선 정렬
    // union-find 를 통해 싸이클확인
    // 무조건 최소 간선만을 선택하는 방식

    static void makeSet() {
        parents = new int[N];
        
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    static int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;

        if (pa <= pb ) parents[pb] = parents[pa];
        else parents[pa] = parents[pb];
        return true;
    }

    static int number(char alp){
        if (alp == '0') return 0;
        if (alp <= 'Z') return alp-'A' + 27;
        else return alp-'a' + 1;
    }
}