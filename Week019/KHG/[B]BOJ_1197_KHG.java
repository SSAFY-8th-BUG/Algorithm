import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	
	static void prim() {
		visited[0]=true;
		for(Edge e : neighbor[0]) {
			hp.add(new Edge(e.w, e.n));
		}
		num=1;
		
		while (!hp.isEmpty()) {
			if(num==V)break;
			Edge cur = hp.poll();
			int n=cur.n;
			if(!visited[n]) {
				answer+=cur.w;
				num++;
				visited[n]=true;
				for(Edge e : neighbor[n]) {
					if(!visited[e.n]) {
						hp.add(new Edge(e.w, e.n));
					}
				}
			}
		}
	}
	
	static int V,E, num;
	static long answer;
	static List<Edge>[] neighbor;
	static PriorityQueue<Edge> hp;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		V = Integer.parseInt(stn.nextToken());
		E = Integer.parseInt(stn.nextToken());
		
		neighbor = new List[V];
		for(int i=0; i<V; i++) {
			neighbor[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<E; i++) {
			stn=new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(stn.nextToken())-1;
			int n2 = Integer.parseInt(stn.nextToken())-1;
			int w = Integer.parseInt(stn.nextToken());
			neighbor[n1].add(new Edge(w,n2));
			neighbor[n2].add(new Edge(w,n1));
			
			
		}
		
		hp = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
		visited=new boolean[V];
		prim();
		System.out.println(answer);		
		
		
	}
	
	static class Edge{
		int w, n;
		Edge(int w, int n){
			this.w=w;
			this.n=n;
		}
	}
}
