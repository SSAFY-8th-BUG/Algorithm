import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	
	static int N, V;
	static int[][] mat, mat2, dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	static int[] dist;
	static Map<Integer,Integer>[] edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		N = Integer.parseInt(br.readLine());
		
		mat = new int[N][N];
		for(int y=0; y<N; y++) {
			stn=new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		
		init(); //같은 높이끼리 그룹을 묶기
		getEdges(); //이웃한 그룹간 높이 차 구하기
		dijkstra(); //다익스트라로 최대 높이차의 최소 구하기
		
		int s=1, e=mat2[N-1][N-1]; //시작과 끝 그룹 명
		System.out.println(dist[e]-dist[s]);
		
		
		
	}
	
	static void init() { //같은 높이끼리 그룹을 묶어서 mat2에 적기
		mat2 = new int[N][N];
		int n=0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(mat2[y][x]!=0)continue;
				mat2[y][x]=++n;
				Deque<Point> que = new ArrayDeque<>();
				que.add(new Point(x,y));
				int num=mat[y][x];
				while(!que.isEmpty()) {
					Point cur = que.pollFirst();
					int cx=cur.x, cy=cur.y;
					for(int dr=0; dr<4; dr++) {
						int nx=cx+dirs[dr][0];
						int ny=cy+dirs[dr][1];
						if(nx<0 || ny<0 || nx>=N || ny>=N || mat2[ny][nx]!=0 || mat[ny][nx]!=num) continue;
						mat2[ny][nx]=n;
						que.add(new Point(nx,ny));
					}
				}
			}
		}
		/*for(int y=0; y<N; y++) {
			System.out.println(Arrays.toString(mat2[y]));
		}*/
		V=n;
	}
	
	static void getEdges() { //이웃한 그룹간 높이차(edge) 구하기
		edges = new Map[V+1];
		for(int i=1; i<=V; i++) {
			edges[i]= new HashMap<>();
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				for(int dr=0; dr<2; dr++) {
					int nx=x+dirs[dr][0];
					int ny=y+dirs[dr][1];
					if(nx==N || ny==N)continue;
					if(mat[y][x] != mat[ny][nx]) {
						int n1=mat2[y][x];
						int n2=mat2[ny][nx];
						int w=Math.abs(mat[y][x]-mat[ny][nx]);
						if(!edges[n1].containsKey(n2)) edges[n1].put(n2, w);
						if(!edges[n2].containsKey(n1)) edges[n2].put(n1, w);
					}
				}
			}
		}
	}
	
	static void dijkstra() {
		dist=new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1]=0;
		PriorityQueue<Node> hp = new PriorityQueue<>((o1,o2) -> o1.w-o2.w);
		hp.add(new Node(1,0));
		while(!hp.isEmpty()) {
			Node cur = hp.poll();
			int cn=cur.n, w=cur.w;
			for(int nn : edges[cn].keySet()) {
				int d=Math.max(edges[cn].get(nn), w); //최대 높이차(최대 edge)
				if(d < dist[nn]) {
					dist[nn]= d;
					hp.add(new Node(nn, d));
				}
			}
		}
		//System.out.println(Arrays.toString(dist));
	}
	
	static class Node{
		int n,w;
		Node(int n, int w){
			this.n=n;
			this.w=w;
		}
	}

}
