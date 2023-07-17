package studygroup.Week031;
import java.util.*;
public class PG_가장먼노드 {
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;
    static ArrayList<Integer>[] map;
    static ArrayList<Integer> list;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new boolean[n+1];
        map = new ArrayList[n+1];
        list = new ArrayList<>();
        for(int i=0; i<=n; i++){
            map[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<edge.length; i++){
            int x = edge[i][0];
            int y = edge[i][1];
            map[x].add(y);
            map[y].add(x);
        }
        bfs(1);
        return list.size();
    }
    public static void bfs(int str){
        visited[str] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{str,0});

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int cur = tmp[0];
            int dep = tmp[1];
            if(dep > max){
                max = dep;
                list.clear();
                list.add(dep);
            }else if(dep == max){
                list.add(dep);
            }

            for(int i=0; i<map[cur].size(); i++){
                int m = map[cur].get(i);
                if(visited[m]) continue;
                visited[m] = true;
                q.add(new int[]{m,dep+1});
            }
        }
    }
}
