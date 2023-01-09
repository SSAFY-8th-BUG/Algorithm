import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	
	static boolean allTest() {
		for(int n=0; n<V; n++) {
			if(!bellmanFord(n)) return false;
		}
		return true;
	}
	
	static boolean bellmanFord(int start) {
		int[] dist = new int [V];
		Arrays.fill(dist, 987654321);
		dist[start]=0;
		boolean isUpdate;
		for(int i=0; i<V; i++) {
			isUpdate=false;
			for(int j=0; j<total; j++) {
				int cn=edges[j].n1, nn=edges[j].n2, w=edges[j].w;
				if(dist[cn]!=Integer.MAX_VALUE && dist[nn] > dist[cn] + w) {
					if(i==V-1)return false;
					isUpdate=true;
					dist[nn] = dist[cn]+w;
				}	
			}
			if(!isUpdate)break;
			
		}
		return true;
	}
	
	static int V,E, W, total;
	static Edge[] edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<T; tc++) {
			stn = new StringTokenizer(br.readLine());
			V = Integer.parseInt(stn.nextToken());
			E = Integer.parseInt(stn.nextToken());
			W = Integer.parseInt(stn.nextToken());
			total= E*2+W;
			edges = new Edge[total];
			for(int i=0; i<E; i++) {
				stn = new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(stn.nextToken())-1;
				int n2=Integer.parseInt(stn.nextToken())-1;
				int w=Integer.parseInt(stn.nextToken());
				edges[2*i]=new Edge(n1,n2,w);
				edges[2*i+1]=new Edge(n2,n1,w);
			}
			for(int i=0; i<W; i++) {
				stn = new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(stn.nextToken())-1;
				int n2=Integer.parseInt(stn.nextToken())-1;
				int w=Integer.parseInt(stn.nextToken());
				edges[2*E+i]=new Edge(n1,n2,-w);
			}
			if(bellmanFord(0)) sb.append("NO\n");
			else sb.append("YES\n");
			
		}
		System.out.println(sb);
		
		
		
	}
	
	static class Edge{
		int n1, n2, w;
		Edge(int n1, int n2, int w){
			this.n1=n1;
			this.n2=n2;
			this.w=w;
		}
	}
	

}
