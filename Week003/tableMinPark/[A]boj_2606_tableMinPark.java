import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_2606_tableMinPark{
    static int answer;
    static List<Integer>[] graph;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            graph[B].add(A);
        }

        answer = 0;
        v = new boolean[N + 1];
        v[1] = true;
        dfs(1);
        
        System.out.println(answer);
        br.close();
    }

    static void dfs(int now){
        
        for (int next : graph[now]){
            if (v[next]) continue;
            answer++;
            v[next] = true;
            dfs(next);
        }
    }
}