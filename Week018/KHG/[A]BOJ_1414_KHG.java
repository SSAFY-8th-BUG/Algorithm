import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main { 
	
	static int N;
	static int[][] lens;
	static int totalLen;
	static int[] root;
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lens = new int[N][N];
        totalLen=0;
        
        
        for(int y=0; y<N; y++) {
        	String row = br.readLine();
        	for(int x=0; x<N; x++) {
        		char c =row.charAt(x);
        		if(c=='0') {
        			lens[y][x]=0;
        		}
        		else {
        			lens[y][x] = (c <= 90) ? c-38 : c-96; 
        			totalLen+=lens[y][x];
        		}
        	}
        }
        if(N==1) {
        	System.out.println(lens[0][0]);
        	return;
        }
        
        PriorityQueue<Edge> hp = new PriorityQueue<>((o1,o2)-> o1.w-o2.w);
        for(int y=0; y<N-1; y++) {
        	for(int x=y+1; x<N; x++) {
        		if(lens[y][x]==0 && lens[x][y]==0)continue;
        		int w = Math.min(lens[y][x], lens[x][y]);
        		if(w==0) {
        			w = Math.max(lens[y][x], lens[x][y]);
        			hp.add(new Edge(w,y+1,x+1));
        		}else {
        			hp.add(new Edge(w,y+1,x+1));
        		}
        	}
        }
    
        root=new int[N+1];
        for(int i=1; i<=N; i++) {
        	root[i]=i;
        }
        int edgeNum=0; //연겔된 엣지 수
        int edgeSum=0; //엣지 길이합
        
        while(!hp.isEmpty()) {
        	if(edgeNum==N-1) break;
        	Edge cur = hp.poll();
        	int w=cur.w;int n1 =  cur.n1; int n2= cur.n2;
        	//System.out.println(w+","+n1+","+n2);
        	if(findRoot(n1)==findRoot(n2))continue; //순환되면
        	union(n1,n2); //합치기
        	edgeSum+=w; 
        	edgeNum++;
        }
        
        if(edgeNum<N-1) { //모두연결 못함
        	System.out.println(-1);
        }else { //모두 연결함
        	System.out.println(totalLen-edgeSum);
        }
    }
	
	static int findRoot(int n) {
		if(n==root[n]) return n;
		else {
			return root[n]=findRoot(root[n]);
		}
	}
	
	static void union(int n1, int n2) {
		int r1=findRoot(n1);
		int r2=findRoot(n2);
		if(r1==r2){
			return;
		}else if(r1<r2){
			root[r2]=r1;
		}else {
			root[r1]=r2;
		}
	}
	
	static class Edge{
		int w,n1,n2;
		Edge(int w, int n1, int n2){
			this.w=w;
			this.n1=n1;
			this.n2=n2;
		}
	}
	
}