package studygroup.Week28;
import java.util.*;
public class PG_부대복귀 {
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int dest;
    static int N;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        list = new ArrayList[n+1];
        dest = destination;
        N = n;
        for(int i=0; i<n+1; i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<roads.length; i++){
            list[(roads[i][0])].add(roads[i][1]);
            list[(roads[i][1])].add(roads[i][0]);
        }
        for(int i=0; i<sources.length; i++){
            answer[i] = bfs(sources[i]);
        }

        return answer;
    }
    public static int bfs(int start){
        visited = new boolean[N+1];
        visited[start] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start,0});

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(tmp[0] == dest){
                return tmp[1];
            }
            for(int l : list[tmp[0]]){
                if(!visited[l]){
                    visited[l] = true;
                    q.add(new int[]{l,tmp[1]+1});
                }
            }
        }
        return -1;
    }
}
