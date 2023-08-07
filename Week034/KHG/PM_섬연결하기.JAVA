import java.util.*;

class Solution {
    static int answer,N;
    static int[] root;
    static PriorityQueue<Edge> hp = new PriorityQueue<>((o1,o2)->o1.w-o2.w);
    public int solution(int n, int[][] costs) {
        answer = 0;
        N =n;
        root = new int[n];
        for(int i=0; i<n; i++){
            root[i]=i;
        }
        for(int[] cost : costs){
            hp.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        while(!hp.isEmpty()){
            Edge cur = hp.poll();
            int n1 = cur.n1;
            int n2 = cur.n2;
            int w = cur.w;
            union(n1,n2,w);
        }
        return answer;
    }
    
    static int find(int n) { 
		if(root[n]==n) {
			return n;
		}else {
			return root[n]=find(root[n]);
		}
	}
	
	static void union(int n1, int n2, int w) { 
		int r1=find(n1);
		int r2=find(n2);
		if(r1==r2) return;
        // System.out.println(n1+" "+n2);
        // System.out.println(Arrays.toString(root));
		if(r1>r2) {
			root[r1]=r2;
		}else {
			root[r2]=r1;
		}
        System.out.println(Arrays.toString(root));
		answer+=w;
	}
    
    static class Edge{
        int n1,n2,w;
        public Edge(int n1, int n2, int w) {
            this.n1=n1;
            this.n2=n2;
            this.w=w;
        }
    
		
	}

    
}