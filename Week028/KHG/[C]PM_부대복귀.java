import java.util.*;
class Solution {
    static int N;
    static List<Integer>[] graph;
    static int[] dist;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        init(n, roads, sources, destination);
        getDist(destination);
        for(int i=0; i<sources.length; i++){
            int node = sources[i];
            answer[i] = dist[node];
        }
        return answer;
    }
    static void init(int n, int[][] roads, int[] sources, int destination){
        N=n;
        graph = new List[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] road : roads){
            int n1 = road[0];
            int n2 = road[1];
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        dist = new int[N+1];
        for(int i=1; i<=N; i++){
            dist[i] = -1;
        }
    }
    
    static void getDist(int sn){
        dist[sn] = 0;
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(sn,0));
        while(!que.isEmpty()){
            Node cur = que.pollFirst();
            int cn = cur.n;
            int cw = cur.w;
            for(Integer nn : graph[cn]){
                int nw = cw+1;
                if(dist[nn]<=nw && dist[nn]!=-1) continue;
                dist[nn]=nw;
                que.add(new Node(nn,nw));
            }            
        }
        //System.out.println(Arrays.toString(dist));
    }
    
    static class Node{
        int n, w;
        Node(int n, int w){
            this.n=n;
            this.w=w;
        }
    }
    
}