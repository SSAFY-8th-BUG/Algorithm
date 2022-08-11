package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static final int SEA=0;
	
	static int N,M;
	static int[][] map;
	static int[][] melt;
	
	static int ice_cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		melt=new int[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if (map[i][j]>0) ice_cnt++;
			}
		}
		
		int Time=1;
		while (true) {
			meltIce ();
			
			// 얼음이 다 녹을 때까지 분리되지 않은 경우
			if (ice_cnt==0) {
				System.out.println("0");
				break;
			}
			
			if (!check()) {
				System.out.println(Time);
				break;
			}
			Time++;					
		}
	}
	
	private static boolean check () {
		
		boolean[][] visited=new boolean[N][M];
		Queue <Pair>q=new ArrayDeque<>();
		int cnt=1;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				
				// 빙산을 찾았을 때 이와 인접한 빙산의 개수를 센다
				if (map[i][j]!=SEA) {
					
					visited[i][j]=true;
					q.add(new Pair (i,j));
					
					while (!q.isEmpty()) {
						Pair cur=q.poll();
						int y=cur.y;
						int x=cur.x;
						
						for (int d=0; d<4; d++) {
							int ny=y+dy[d];
							int nx=x+dx[d];
							
							if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
							if (map[ny][nx]!=SEA && !visited[ny][nx]) {
								visited[ny][nx]=true;
								q.add(new Pair (ny, nx));
								cnt++;
							}
							
						}
					}		
					
					// 위에서 구한 빙산의 개수와 원래 빙산의 개수가 동일하면 true, 아니면 false
					if (cnt==ice_cnt) return true;
					else return false;
						
				}
			}
		}
		return false;
	}
	
	/*
	 *  빙산과 인접한 바다 개수를 melt[][]에 저장하고
	 *  개수 탐색이 끝나면 빙산을 녹인다
	 *  
	 *  이때 빙산이 다 녹으면 ice_cnt를 줄여주어 빙산이 사라졌음을 표시
	 */
	
	private static void meltIce () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				
				if (map[i][j]>0) {
					int near_sea=0;
					
					for (int d=0; d<4; d++) {
						int ny=i+dy[d];
						int nx=j+dx[d];
						
						if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
						if (map[ny][nx]==SEA) near_sea++;
					}
					
					melt [i][j]=near_sea;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j]-=melt[i][j];
				
				if (melt[i][j]>0 && map[i][j]<=0) {
					melt[i][j]=0;
					map[i][j]=0;
					ice_cnt--;
				}
			}
		}
	}
	
	private static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
