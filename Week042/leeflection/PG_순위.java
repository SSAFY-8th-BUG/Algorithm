package studygroup.Week042;
import java.util.*;
public class PG_순위 {
    static ArrayList<Integer>[] win;
    static int ans;
    static int[][] info;
    public int solution(int n, int[][] results) {
        win = new ArrayList[n+1];

        for(int i=1; i<=n ; i++){
            win[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<results.length; i++){
            int a= results[i][0];
            int b = results[i][1];
            win[a].add(b);
        }
        info = new int[n+1][2];
        solve(n);
        return ans;
    }
    public static void solve(int n){
        for(int i=1; i<=n; i++){
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[n+1];

            visited[i] = true;
            q.add(i);

            while(!q.isEmpty()){
                int val = q.poll();
                for(int loser : win[val]){
                    if(visited[loser]) continue;
                    visited[loser] = true;
                    q.add(loser);
                    info[i][0]++;
                    info[loser][1]++;
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(info[i][0] + info[i][1] == n-1) ans++;
        }
    }
}
