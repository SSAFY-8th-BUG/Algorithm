import java.util.*;

class Solution {
    static int[][] inout;
    static int N;
    static List<Integer>[] graph;
    static int[] answer;
    static boolean[] visited;
    public int[] solution(int[][] edges) {
        answer = new int[]{0,0,0,0};
        for(int[] edge : edges){
            int n1=edge[0];
            int n2=edge[1];
            N = Math.max(N,Math.max(n1,n2));
        }
        inout = new int[N+1][2];
        graph = new List[N+1];
        for(int i=1; i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            int n1=edge[0];
            int n2=edge[1];
            graph[n1].add(n2);
            inout[n1][1]++;
            inout[n2][0]++;
        }
        int out=0;
        for(int i=1; i<=N; i++){
            if(inout[i][0]==0){
                if(inout[i][1]>out){
                    out=inout[i][1];
                    answer[0] = i;
                }
            }
        }
        
        visited = new boolean[N+1];
        visited[answer[0]]=true;
        for(int nn : graph[answer[0]]){
            check(nn);
        }
        return answer;
    }
    
    void check(int sn){
        visited[sn]=true;
        Deque<Integer> que = new ArrayDeque<>();
        que.add(sn);
        while(!que.isEmpty()){
            int cn = que.pollLast();
            if(graph[cn].size()==0){
                answer[2]++;
                return;
            }
            if(graph[cn].size()==1){
                int nn = graph[cn].get(0);
                if(visited[nn]){
                    answer[1]++;
                    return;
                }
            }
            if(graph[cn].size()>1){
                if(inout[cn][0]>1 && inout[cn][1]>1){
                    answer[3]++;
                    return;
                }
            }
            for(int nn:graph[cn]){
                if(visited[nn])continue;
                visited[nn]=true;
                que.addLast(nn);
            }
        }
    }
}