import java.io.*;
import java.util.*;

// 벨만 포드
// M : 간선
// W : 정점
public class bj_1865_tableMinPark {

    static int TC, N, M, W;    
    static int[][] adj;
    static int[] values;
    static final int INF = 2500 * 10001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < TC; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            adj = new int[N + 1][N + 1];
            values = new int[N + 1];
            Arrays.fill(values, INF);
            values[0] = values[1] = 0;

            for (int i = 1; i <= N; i++) Arrays.fill(adj[i], INF);

            // 도로
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                adj[S][E] = Math.min(adj[S][E], T);
                adj[E][S] = adj[S][E];
            }

            // 웜홀
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                adj[S][E] = -T;
            }

            if (BellmanFord()) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
        br.close();
    }
    public static boolean BellmanFord() {
        boolean updated = false;
        for (int n = 0; n < N - 1; n++) {
            updated = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (values[i] + adj[i][j] < values[j]) {
                        values[j] = values[i] + adj[i][j];
                        updated = true;
                    }
                }
            }
            if (!updated) break;
        }
 
        if (updated) {
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (values[i] + adj[i][j] < values[j]) return true;
        }
 
        return false;
    }
}
