import java.io.*;
import java.util.*;

// 서로소집합사용
// MST
public class boj_14621_tableMinPark {

    static int N, M, answer;
    static PriorityQueue<Node> virtex;

    static boolean[] gender;
    static int[] parents;

    static class Node {
        int a;
        int b;
        int c;
        public Node (int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        gender = new boolean[N + 1];
        for (int i = 1; i <= N; i++){
            if (st.nextToken().charAt(0) == 'M') gender[i] = true;
            else gender[i] = false;
        }

        virtex = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            virtex.add(new Node(u, v, d));
        }

        answer = 0;
        int count = 0;
        makeSet();
        while(!virtex.isEmpty()){
            if (count == N - 1) break;
            Node now = virtex.poll();

            if (findSet(now.a) != findSet(now.b) && gender[now.a] != gender[now.b]) {
                union(now.a, now.b);
                answer += now.c;
                count++;
            }
        }
        if (count < N - 1) answer = -1;

        System.out.println(answer);
        br.close();
    }

    static void makeSet(){
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++){
            parents[i] = i;
        }
    }

    static void union(int a, int b){
        a = findSet(a);
        b = findSet(b);
        if (a > b) parents[b] = a;
        else parents[a] = b;
    }

    static int findSet(int x){
        if (parents[x] == x) return x;
        return parents[x] = findSet(parents[x]);
    }
}
