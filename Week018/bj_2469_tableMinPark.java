import java.io.*;

// 실패
// 다시
public class bj_2469_tableMinPark {

    static int K, N, T;
    static Node[] start;
    static char[][] map;
    static class Node {
        char alp;
        int idx;

        public Node (char alp, int idx) {
            this.alp = alp;
            this.idx = idx;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        start = new Node[K];
        char[] input = br.readLine().toCharArray();
        for (int i = 0; i < K; i++){
            start[i] = new Node(input[i], i);
        }

        map = new char[K][N];
        for (int i = N - 1; i >= 0; i--) {
            map[i] = br.readLine().toCharArray();
            if (map[i][0] == '?') {
                T = i;
                for (int j = 0; j < K - 1; j++) map[i][j] = '*';
            }
        }

        for (Node node : start) {
            if (node.idx - 1 >= 0 && map[T][node.idx - 1] == '-') continue;
            else {
                if (check(node.idx, node.alp)) continue;

                map[T][node.idx - 1] = '-';
                if (check(node.idx, node.alp)) continue;

                map[T][node.idx - 1] = '*';
                map[T][node.idx] = '-';
                if (check(node.idx, node.alp)) continue;

                System.out.println("x");
                break;
            }
        }

        for (int i = 0; i < K - 1; i++) {
            System.out.print(map[T][i]);
        }
        br.close();
    }

    static boolean check(int idx, char alp) {
        for (int depth = 0; depth < N; depth++) {
            // 오른쪽
            if (idx < K - 1 && map[depth][idx] == '-') {
                idx++;
            } 
            // 왼쪽
            else if (idx - 1 >= 0 && map[depth][idx - 1] == '-') {
                idx--;
            }
        }

        if (idx == alp-'A') return true;
        else return false;
    }
}