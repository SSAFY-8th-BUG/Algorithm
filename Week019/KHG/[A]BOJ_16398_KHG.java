import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	
	static int findRoot(int n) {
		if(roots[n]==n)
			return n;
		return roots[n]=findRoot(roots[n]);
	}
	
	static void union(int n1, int n2) {
		int r1 = roots[n1];
		int r2 = roots[n2];
		if(r1<r2) {
			roots[r2]=r1;
		}
		else if(r1>r2) {
			roots[r1]=r2;
		}
	}
	
	static void krus() {
		roots=new int[N];
		for(int i=1; i<N; i++) {
			roots[i] = i;
		}
		
		hp = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				hp.add(new Edge(mat[i][j], i, j));
			}
		}
		
		while(!hp.isEmpty()) {
			Edge cur = hp.poll();
			int w=cur.w, n1=cur.n1, n2=cur.n2;
			int r1=findRoot(n1);
			int r2=findRoot(n2);
			if(r1==r2) continue;
			answer+=w;
			union(n1,n2);
			
			
		}
	}
	
	static int N;
	static long answer;
	static int[][] mat;
	static int[] roots;
	static PriorityQueue<Edge> hp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		N = Integer.parseInt(br.readLine());
		mat=new int[N][N];
		for(int y=0; y<N; y++) {
			stn  = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				mat[y][x] = Integer.parseInt(stn.nextToken());
			}
		}
		
		krus();
		System.out.println(answer);
		
		
		
	}
	
	static class Edge{
		int w, n1,n2;
		Edge(int w, int n1, int n2){
			this.w=w;
			this.n1=n1;
			this.n2=n2;
		}
	}
}
