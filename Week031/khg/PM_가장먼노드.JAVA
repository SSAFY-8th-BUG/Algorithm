import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int answer,N;
    public int solution(int n, int[][] edge) {
        answer = 0;
        N=n;
        graph = new List[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] vertex : edge){
            int n1 = vertex[0];
            int n2 = vertex[1];
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        find();
        
        return answer;
    }
    
    static void find(){
        boolean[] visited = new boolean[N+1];
        visited[1]=true;
        int maxDist=0;
        int cnt=0;
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(1,0));
        while(!que.isEmpty()){
            Node cur = que.pollFirst();
            int cn = cur.n;
            int cw = cur.w;
            for(int nn : graph[cn]){
                if(visited[nn])continue;
                visited[nn]=true;
                que.add(new Node(nn,cw+1));
                if(cw+1>maxDist){
                    maxDist = cw+1;
                    cnt=1;
                }else if(cw+1==maxDist){
                    cnt+=1;
                }
            }
        }
        answer=cnt;
    }
    
    static class Node{
        int n,w;
        Node(int n, int w){
            this.n=n;
            this.w=w;
        }
    }
}