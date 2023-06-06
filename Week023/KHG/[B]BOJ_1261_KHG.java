import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[][] mat;
	static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());
		mat = new int[M][N];
		for(int y=0; y<M; y++) {
			String row = br.readLine();
			for(int x=0; x<N; x++) {
				if(row.charAt(x)=='0')
					mat[y][x] = 1;
				else
					mat[y][x] = 0;
			}
		}
		go();
	}

	
	static void go() {
		int[][] visited = new int[M][N];
		for(int y=0; y<M; y++) {
			for(int x=0; x<N; x++) {
				visited[y][x] = M*N;
			}
		}
		Deque<Node> que = new ArrayDeque<>();
		if(mat[0][0]==0) {
			que.add(new Node(0,0,1));
			visited[0][0] =1;
		}
		
		else {
			que.add(new Node(0,0,0));
			visited[0][0] = 0;
		}
		while(!que.isEmpty()) {
			Node cur = que.pollFirst();
			int cx=cur.x, cy=cur.y, cn = cur.n;
			for(int dr=0; dr<4; dr++) {
				int nx = cx+dirs[dr][0];
				int ny = cy+dirs[dr][1];
				if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
				int nn=cn;
				if(mat[ny][nx]==0) nn++;
				if(visited[ny][nx]<=nn)continue;
				visited[ny][nx]=nn;
				que.add(new Node(nx,ny,nn));
				
			}
		}
		System.out.println(visited[M-1][N-1]);
	}
	
	static class Node{
		int x,y,n;
		Node(int x, int y, int n){
			this.x=x;
			this.y=y;
			this.n=n;
		}
	}

}
