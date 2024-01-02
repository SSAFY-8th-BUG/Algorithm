import java.util.*;

class Solution {
    static int[] info;
    static List<Integer>[] graph;
    static int N, answer;
    static boolean[] canGo;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        N = info.length;
        graph = new List[N];
        answer=0;
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            int n1 = edge[0];
            int n2 = edge[1];
            graph[n1].add(n2);
        }
        
        
        canGo = new boolean[N];
        for(int nn : graph[0]){
            canGo[nn]=true;
        }
        
        rec(0,1);
        return answer;
    }
    
    void rec(int w, int g){
        if(g>answer)answer=g;
        
        for(int cn=0; cn<N; cn++){
            if(!canGo[cn])continue;
            if(info[cn]==1 && g-w<=1)continue;
            canGo[cn]=false;
            for(int nn:graph[cn]){
                canGo[nn]=true;
            }
            
            if(info[cn]==1) rec(w+1,g);
            else rec(w,g+1);
            
            canGo[cn]=true;
            for(int nn:graph[cn]){
                canGo[nn]=false;
            }
        }
    }
    
}
