import java.util.*;

class Solution {
    static List<Node> adj[];
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adj=new ArrayList[n+1];
        for (int i=1; i<=n; i++)
            adj[i]=new ArrayList<>();
        
        
        for (int[] p : paths) {
            int u=p[0];
            int v=p[1];
            int cost=p[2];
            
            
            //#1. 출입구일 경우 --> 단방향
            //#2. 정상인 경우 --> 여기서 출발하는 경우는 없음
            //     출입구에서 정상까지 최단거리이면 정상에서 출입구까지도 최단거리 이므로
            if (isGate(u, gates) || isSummit(v, summits)) { 
                adj[u].add(new Node (v,cost));
            } else if (isGate (v, gates) || isSummit (u, summits)) {
                adj[v].add(new Node (u, cost));
            } else {
                adj[u].add (new Node (v,cost));
                adj[v].add (new Node (u, cost));
            }
        }
        
        return dijkstra (n, gates, summits);
    }
    
    static int[] dijkstra (int n, int[] gates, int[] summits) {
        Queue<Node> q=new ArrayDeque<>();
        int[] intensity=new int[n+1];
        
        Arrays.fill (intensity, Integer.MAX_VALUE);
        
        for (int gate : gates) {    // 출입구를 전부 queue에 넣고 시작
            q.add(new Node (gate, 0));
            intensity[gate]=0;
        }
        
        while (!q.isEmpty ()) {
            Node cur=q.poll();
            
             //현재 가중치가 저장된 가중치보다 큰 경우
            if (cur.cost > intensity[cur.v]) continue;  
            
            for (int i=0; i<adj[cur.v].size(); i++) {
                Node next=adj[cur.v].get(i);
                
                int dist=Math.max (intensity [cur.v], next.cost);
                if (intensity[next.v]>dist) {
                    intensity[next.v]=dist;
                    q.add(new Node (next.v, dist));
                }
            }
        }
        
        int mn=Integer.MAX_VALUE; // 산봉우리 번호
        int mw=Integer.MAX_VALUE; // 최솟값
        
        Arrays.sort (summits);
        
        for (int summit : summits) {
            if (intensity [summit] < mw) {
                mn=summit;
                mw=intensity[summit];
            }
        }
        
        return new int[] {mn, mw};
    }
    
    
   
    static boolean isGate (int no, int[] gates) {
        for (int n : gates) {
            if (no==n) return true;
        }
        return false;
    }
    
    static boolean isSummit (int no, int[] summits) {
        for (int n : summits) {
            if (no==n) return true;
        }
        return false;
    }
    
    static class Node {
        int v,cost;
        
        Node (int v, int cost) {
            this.v=v;
            this.cost=cost;
        }
    }
    
}