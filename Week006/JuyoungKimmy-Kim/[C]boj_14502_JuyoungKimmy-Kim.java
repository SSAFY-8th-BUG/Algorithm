package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {

	static final int EMPTY=0, WALL=1, VIRUS=2;
	
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int N,M, safeArea, ans;
	static int[][] map;
	static List<int[]> virus=new ArrayList<>();;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==VIRUS)
					virus.add(new int[] {i,j});
				if (map[i][j]==EMPTY)
					safeArea++;
			}
		}
		
		dfs (0,0,0);
		System.out.println(ans-3);
		
	}
	
	private static void dfs (int y, int x, int cnt) {
		if (cnt==3) {
			int ret=spreadVirus ();
			ans=Math.max(ret, ans);
			return ;
		}
		
		for (int i=y; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==EMPTY) {
					map[i][j]=WALL;
					dfs (i, j, cnt+1);
					map[i][j]=EMPTY;
				}
			}
		}
	}
	
	private static int spreadVirus () {
		
		//print();
		
		boolean[][] visited=new boolean[N][M];
		
		Queue <int[]>q=new ArrayDeque<>();
		for (int i=0; i<virus.size(); i++) {
			int y=virus.get(i)[0];
			int x=virus.get(i)[1];
			q.add(new int[] {y,x});
		}
		
		int infectedArea=0;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if (map[ny][nx]==WALL || visited[ny][nx]) continue;
				
				if (map[ny][nx]==EMPTY) {
					visited[ny][nx]=true;
					q.add(new int[] {ny, nx});
					infectedArea++;
				}
			}
		}
		
		//System.out.println(infectedArea);
		return safeArea-infectedArea;
	}
	
	static void print () {
		System.out.println("================================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
