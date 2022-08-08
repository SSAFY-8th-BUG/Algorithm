import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_1991_tableMinPark{
    static class Node{
        char l;
        char r;

        public Node(char l, char r){
            this.l = l;
            this.r = r;
        }
    }

    static int N;
    static Map<Character, Node> graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        graph = new HashMap<>();

        // key -> 부모노드 , value -> 자식노드
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char n = st.nextToken().charAt(0);
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);
            graph.put(n, new Node(l, r));
        }

        // 전위순회
        front('A');
        sb.append("\n");
        // 중위순회
        middle('A');
        sb.append("\n");
        // 후위순회
        rear('A');
        
        System.out.println(sb);
        br.close();
    }

    static void front(char now){
        Node next = graph.get(now);
        sb.append(now);                     // 부모탐색
        if (next.l != '.') front(next.l);   // 왼쪽자식탐색
        if (next.r != '.') front(next.r);   // 오른쪽자식탐색
        // 좌우 자식이 없으면 알아서 return
    }

    static void middle(char now){
        Node next = graph.get(now);
        if (next.l != '.') middle(next.l);
        sb.append(now);
        if (next.r != '.') middle(next.r);
        // 우측 자식이 없으면 알아서 return
    }

    static void rear(char now){
        Node next = graph.get(now);
        if (next.l != '.') rear(next.l);
        if (next.r != '.') rear(next.r);
        sb.append(now);
        // 좌우 자식이 없으면 부모노드 탐색하고 알아서 return
    }
}