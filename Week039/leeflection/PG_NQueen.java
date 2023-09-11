package studygroup.Week039;

public class PG_NQueen {
    boolean[] visited;
    int[] pick;
    int count;

    public int solution(int n) {
        visited = new boolean[n];
        pick = new int[n];
        count = 0;

        dfs(n, 0);
        return count;
    }

    public void dfs(int n, int idx) {
        if(n == idx) {
            count++;
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                pick[idx] = i;
                if(check(idx)) dfs(n, idx + 1);
                visited[i] = false;
            }
        }
    }

    public boolean check(int idx) {
        for(int i = 0; i < idx; i++) {
            if(pick[i] == pick[idx]) return false;
            if(Math.abs(idx - i) == Math.abs(pick[idx] - pick[i])) return false;
        }
        return true;
    }
}
