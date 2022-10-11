import java.io.*;
import java.util.*;

public class swea_5648_tableMinPark {

    static int T, N, answer;
    static List<P> list;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static class P{
        int y;
        int x;
        int d;
        int e;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
        public P (int y, int x, int d, int e){
            this.y = y;
            this.x = x;
            this.d = d;
            this.e = e;
        }
        @Override
        public boolean equals(Object obj) {
            P p = (P) obj;
            if (this.y == p.y && this.x == p.x) return true;
            return false;
        }

        @Override
        public String toString() {
            return this.y + " " + this.x;
        }
    }

    static class Node {
        int a;
        int b;
        int dis;
        public Node (int a, int b, int dis){
            this.a = a;
            this.b = b;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list.add(new P(y, x, d, e));
            }

            answer = 0;
            int lSize = list.size();
            PriorityQueue<Node> q = new PriorityQueue<>((n1, n2) -> n1.dis - n2.dis);
            for (int i = 0; i < lSize - 1; i++){
                for (int j = i + 1; j < lSize; j++){
                    P A = list.get(i);
                    P B = list.get(j);

                    int dis = ( Math.abs(A.y - B.y) + Math.abs(A.x - B.x) ) / 2;

                    P nA = new P(A.y + dy[A.d] * dis, A.x + dx[A.d] * dis);
                    P nB = new P(B.y + dy[B.d] * dis, B.x + dx[B.d] * dis);    
                    
                    if ((nA.y == nB.y && nA.x == nB.x) 
                    || (nA.y + dy[A.d] == nB.y && nA.x + dx[A.d] == nB.x
                    &&  nB.y + dy[B.d] == nA.y && nB.x + dx[B.d] == nA.x)){
                        q.add(new Node(i, j, dis));
                    }
                }
            }

            P[] dead = new P[list.size()];
            while(!q.isEmpty()){
                Node now = q.poll();

                P A = list.get(now.a);
                P B = list.get(now.b);

                if (dead[now.a] != null && dead[now.b] != null ) continue;

                if (dead[now.a] != null){
                    answer += B.e;
                    dead[now.b] = new P(B.y, B.x);   
                    continue;
                }
                if (dead[now.b] != null) {
                    answer += A.e;
                    dead[now.a] = new P(A.y, A.x);           
                    continue;
                }

                dead[now.a] = new P(A.y, A.x);
                dead[now.b] = new P(B.y, B.x);
                answer += A.e + B.e;
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}