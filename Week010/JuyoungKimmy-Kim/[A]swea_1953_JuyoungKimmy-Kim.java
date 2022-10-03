package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음 문제만 보고 그냥 bfs쓰는 쉬운 문제인줄 알았음
 * 그래서 문제를 대충 읽고 문제 풀이를 시작
 * 
 * 1. 0인 곳은 움직일 수 없다는 것을 놓침
 * 2. 이전 파이프와 현재 파이프가 연결 되어 있어야 움직일 수 있다는 점 고려하지 않음
 * => 문제를 제대로 안 읽어서 시간 낭비 (쉬워 보이는 문제도 문제 제대로 읽자 제발!)
 * 
 */
public class SWEA1953 {
							//동,서,남,북
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static boolean done=false;
	static int T,N,M,R,C,L,ans;
	static int[][] map, dist;

	static Queue<int[]> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			dist=new int[N][M];
			
			q=new ArrayDeque<>();
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			done=false;
			ans=0;
			bfs();
			System.out.println("#"+tc+" "+ans);
			
		}

	}
	
	static void bfs () {

		q.add(new int[] {R,C});
		dist[R][C]=1;
		ans++;
		
		int ny=0, nx=0;
		
		while (!q.isEmpty() && !done) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			int type=map[y][x];
			
			switch (type) {
			case 1: 
				for (int d=0; d<4; d++) {
					ny=y+dy[d];
					nx=x+dx[d];
					move(ny, nx, d, dist[y][x]);
				}
				break;
			case 2: 
				for (int d=2; d<4; d++) {
					ny=y+dy[d];
					nx=x+dx[d];

					move(ny, nx, d, dist[y][x]);
				}
				break;
			case 3: 
				for (int d=0; d<2; d++) {
					ny=y+dy[d];
					nx=x+dx[d];
					
					move(ny,nx, d, dist[y][x]);
				}
				break;
			case 4: 
				ny=y+dy[0];
				nx=x+dx[0];
				move(ny, nx, 0, dist[y][x]);
				
				ny=y+dy[3];
				nx=x+dx[3];
				move(ny, nx, 3, dist[y][x]);
				
				break;
			case 5: 
				ny=y+dy[0];
				nx=x+dx[0];
				move(ny, nx, 0, dist[y][x]);
				
				ny=y+dy[2];
				nx=x+dx[2];
				move(ny, nx, 2, dist[y][x]);
				
				break;
			case 6: 
				
				ny=y+dy[1];
				nx=x+dx[1];
				move(ny, nx, 1, dist[y][x]);
				
				ny=y+dy[2];
				nx=x+dx[2];
				move(ny, nx, 2, dist[y][x]);
				
				break;
			case 7: 
				ny=y+dy[1];
				nx=x+dx[1];
				move(ny, nx, 1, dist[y][x]);
				
				ny=y+dy[3];
				nx=x+dx[3];
				move(ny, nx, 3, dist[y][x]);
				
				break;
			}	
		}
	}
	
	static void move (int ny, int nx, int dir, int d) {
		
		if (!isInRange (ny, nx) || dist[ny][nx]!=0 || map[ny][nx]==0) return;
		
		int cur=map[ny][nx];
		boolean flag=false;
		
		if (dir==0) {
			if (cur==1 || cur==3 || cur==6 || cur==7) flag=true;
		} else if (dir==1) {
			if (cur==1 || cur==3 || cur==4 || cur==5) flag=true;
		} else if (dir==2) {
			if (cur==1 || cur==2 || cur==4 || cur==7) flag=true;
		} else if (dir==3) {
			if (cur==1 || cur==2 || cur==5 || cur==6) flag=true;
		}
		
		if (flag) {
			dist[ny][nx]=d+1;
			if (dist[ny][nx]>L) {
				done=true;
				return ;
			}
			ans++;
			q.add(new int[] {ny, nx});
		}
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=M) return false;
		return true;
	}
	
	static void print () {
		System.out.println("===============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(dist[i][j]!=0 ? "O " : "X ");
			}
			System.out.println();
		}
	}

}
