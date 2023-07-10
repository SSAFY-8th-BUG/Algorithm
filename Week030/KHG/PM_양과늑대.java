
import java.util.*;

class Solution {
    static int[] info;
    static List<Integer>[] graph;
    static int N, answer;
    static boolean[] canGo;
    
    
    public int solution(int[] info2, int[][] edges) {
        answer = 0;
        info = info2;
        N = info.length;
        graph = new List[N];
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            int n1 = edge[0];
            int n2 = edge[1];
            graph[n1].add(n2);
        }
        
        canGo = new boolean[N];
        for(int ch : graph[0]){
            canGo[ch]=true;
        }
        
        rec(0,1);
        
        
        return answer;
    }
    
    void rec(int w, int g){
        answer = Math.max(g, answer);
        for(int nn=0; nn<N; nn++){
            if(!canGo[nn]) continue;
            if(info[nn]==0){ //양이면
                for(int ch : graph[nn]){
                    canGo[ch]=true;
                }
                canGo[nn]=false;
                
                rec(w,g+1);
                
                for(int ch : graph[nn]){
                   canGo[ch]=false;
                }   
                canGo[nn]=true;
            }else{ //늑대면
                if(w+1<g){
                    for(int ch : graph[nn]){
                        canGo[ch]=true;
                    }
                    canGo[nn]=false;
                    
                    rec(w+1, g);
                    
                    for(int ch : graph[nn]){
                       canGo[ch]=false;
                    }   
                    canGo[nn]=true;
                }
            }
            
        }
    }
}