import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = n;
        int INF = n * n;
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int[] result : results) {
            int a = result[0];
            int b = result[1];
            graph[a][b] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;

                if (graph[i][j] == INF && graph[j][i] == INF) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }
}