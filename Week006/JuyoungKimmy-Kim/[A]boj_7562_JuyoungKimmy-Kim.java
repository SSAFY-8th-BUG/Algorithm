package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {

	static final int INF=98764321;
	
	static final int dy[] = {-2,-2,-1,1,2,2,1,-1};
	static final int dx[] = {-1,1,2,2,1,-1,-2,-2};
	
	static int T, l; 
	static int sy,sx,ey,ex;
	static int[][] map;
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			l=Integer.parseInt(br.readLine());
			map=new int[l][l];
			for (int i=0; i<l; i++)
				Arrays.fill(map[i], INF);
			
			StringTokenizer st=new StringTokenizer (br.readLine());
			sy=Integer.parseInt(st.nextToken());
			sx=Integer.parseInt(st.nextToken());
			st=new StringTokenizer (br.readLine());
			ey=Integer.parseInt(st.nextToken());
			ex=Integer.parseInt(st.nextToken());
			
			bfs();
		}
		
	}
	
	private static void bfs () {
		Queue <int[]> q=new ArrayDeque<>();
		q.add(new int [] {sy, sx});
		map[sy][sx]=0;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			
			if (y==ey && x==ex) {
				System.out.println(map[y][x]);
				return ;
			}
			
			for (int d=0; d<8; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=l || nx>=l) continue;
				
				if (map[ny][nx]> map[y][x]+1) {
					map[ny][nx]=map[y][x]+1;
					q.add(new int [] {ny, nx});
				}
			}
		}
	}

}
