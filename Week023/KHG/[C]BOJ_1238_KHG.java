import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {
	
	static int N,M,X;
	static Map<Integer,Integer>[] graph;
	static int[] sums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());
		X = Integer.parseInt(stn.nextToken());
		graph = new Map[N+1];
		sums = new int[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new HashMap<>();
			sums[i] = Integer.MAX_VALUE;
		}
		for(int i=0; i<M; i++) {
			stn = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(stn.nextToken());
			int n2 = Integer.parseInt(stn.nextToken());
			int w = Integer.parseInt(stn.nextToken());
			graph[n1].put(n2, w);
		}
		
		fromX();
		for(int i=1; i<=N; i++) {
			toX(i);
		}
		
		int answer=0;
		for(int i=1; i<=N;i++) {
			if(sums[i]>answer) answer=sums[i];
		}
		System.out.println(answer);
	}

	static void fromX() {
		PriorityQueue<Node> hp = new PriorityQueue<>((o1,o2) -> o1.w-o2.w);
		hp.add(new Node(X,0));
		sums[X]=0;
		while(!hp.isEmpty()) {
			Node cur = hp.poll();
			int cn = cur.n, cw = cur.w;
			for(int nn: graph[cn].keySet()) {
				int w = graph[cn].get(nn);
				int nw = cw+ w;
				if(sums[nn] > nw) {
					sums[nn]=nw;
					hp.add(new Node(nn, nw));
				}
			}
		}
		
	}
	
	static void toX(int sn) {
		PriorityQueue<Node> hp = new PriorityQueue<>((o1,o2) -> o1.w-o2.w);
		hp.add(new Node(sn,0));
		int[] visited = new int[N+1];
		for(int i=1; i<=N; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		visited[sn]=0;
		
		while(!hp.isEmpty()) {
			Node cur = hp.poll();
			int cn = cur.n, cw = cur.w;
			if(cn==X) {
				sums[sn]+=visited[X];
				return;
			}
			for(int nn: graph[cn].keySet()) {
				int w = graph[cn].get(nn);
				int nw = cw+ w;
				if(visited[nn] > nw) {
					visited[nn]=nw;
					hp.add(new Node(nn, nw));
				}
			}
		}
		
	}
	
	static class Node{
		int n, w;
		Node(int n, int w){
			this.n=n;
			this.w=w;
		}
	}

}
