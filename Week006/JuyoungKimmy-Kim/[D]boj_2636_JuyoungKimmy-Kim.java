package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int N,M, cheese, time, cnt;
	static int[][] map;
	
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
				if (map[i][j]==1) cheese++;
			}
		}
		
		while (cheese!=0) {
			time++;					// 시간 증가
			cnt=cheese;				// 다 녹기 전 치즈 개수 저장
			meltingCheese();
		}
		System.out.println(time);
		System.out.println(cnt);
		
		
		

	}
	
	private static void meltingCheese () {
		Queue <int[]>q=new ArrayDeque<>();
		q.add(new int[] {0,0});
		
		boolean [][] visited=new boolean[N][M];
		visited[0][0]=true;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) continue;
				
				if (map[ny][nx]==1) {
					cheese--;
					map[ny][nx]=0;
				}
				else if (map[ny][nx]==0) {
					q.add(new int[] {ny, nx});
				}
				
				visited[ny][nx]=true;
			}
		}
	}

}
