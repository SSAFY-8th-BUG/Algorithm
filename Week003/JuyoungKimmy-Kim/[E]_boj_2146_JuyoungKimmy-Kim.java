package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int y,x;
	
	Pos (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class BOJ2146 {
	
	static final int SEA=0;
	static final int LAND=-1;
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N;
	static int minDist=Integer.MAX_VALUE;
	static int [][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());

		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==1) map[i][j]=-1;
			}
		}
		
		
		int number=1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==LAND) {
					map[i][j]=number;
					numbering (i,j, number);
					number++;
				}
			}
		}
		
		// 가지치기1 - 섬이 1개일 경우 바로 0 
		if (number==2) {
			System.out.println("0");
			return ;
		}

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]!=SEA) 
					makeBridge (i,j, map[i][j]);
				
				// 가지치기2 - 새로 만든 다리가 1일 때 더 이상 짧은 다리는 없으므로 종료
				if (minDist==1) {
					System.out.println("1");
					return ;
				}
			}
		}	
		System.out.println(minDist);
	}
	
	/*
	 *  #1. 각 섬에 번호를 매김 
	 *  	섬에서 다른 섬으로 이을 때, 자신의 섬인지 다른 섬인지 구분하기 위해
	 */
	
	private static void numbering (int i, int j, int number) {
		Queue <Pos> q=new ArrayDeque<>();
		boolean [][] visited=new boolean [N][N];
		q.add(new Pos (i, j));
		visited[i][j]=true;
		
		while (!q.isEmpty()) {
			Pos cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange (ny,nx)) continue;
				if (map[ny][nx]==LAND && !visited[ny][nx]) {
					map[ny][nx]=number;
					visited[ny][nx]=true;
					q.add(new Pos (ny, nx));
				}
			}
		}
	}
	
	/*
	 * #2. 각 섬의 한 부분에서 다리를 이을 수 있는 지점을 찾음
	 * 		이동 시 자신이 속한 섬이나, 벽에 부딪히지 않고 다른 섬에 도달했을 경우 minDist 갱신
	 */
	
	private static void makeBridge (int i, int j, int idx) {
		
		Queue <Pos> q=new ArrayDeque<>();
		int[][] distance=new int[N][N];
		
		for (int k=0; k<N; k++)
			Arrays.fill(distance[k], -1);
		
		q.add(new Pos (i,j));
		distance[i][j]=0;
		
		while (!q.isEmpty()) {
			
			Pos cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d]; 
				int nx=x+dx[d];
				
				if (!isInRange(ny,nx) || map[ny][nx]==idx || distance[ny][nx]!=-1) continue; 
				//벽이나 자기 자신과 부딪힐 때, 이미 방문한 점일 때
				
				else if (map[ny][nx]==SEA) {
					distance[ny][nx]=distance[y][x]+1;
					q.add(new Pos(ny, nx));
				}
				// 벽도 아니고, 자신도 아니고, 이미 방문한 점도 아니고, 바다도 아닌 경우 => 다른 섬에 도달
				else {
					if (distance[y][x]<minDist) 
						minDist=distance[y][x];
					return ;
				}
			}
		}
	}
	
	private static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}
	
	private static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
		
	}
}
