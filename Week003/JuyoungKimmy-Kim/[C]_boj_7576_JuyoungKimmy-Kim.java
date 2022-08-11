package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//class Pair {
//	int y,x;
//	
//	Pair (int y, int x) {
//		this.y=y;
//		this.x=x;
//	}
//}

public class BOJ7576 {
	
	static final int EMPTY=-1;
	static final int RAW=0;
	static final int EAT=1;
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static int cnt; 			// 익지 않은 토마토의 개수
	static int latest=0;		//가장 최근에 토마토가 익은 시간이 언제인지 확인
	static int M,N; 
	
	static int[][] box;
	static int[][] dist;
	
	static Queue <Pair> q=new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		box=new int[N][M];
		dist=new int[N][M];
		
		for (int i=0; i<N; i++)
			Arrays.fill(dist[i], -1);
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				box[i][j]=Integer.parseInt(st.nextToken());
				if (box[i][j]==EAT) {
					q.add(new Pair (i,j));
					dist[i][j]=0;
				}
				else if (box[i][j]==RAW) cnt++;
			}
		}
		
		if (q.size()==M*N) {
			System.out.println("0");
			return ;
		}
		
		bfs();
		
		if (cnt==0) System.out.println(latest);
		else System.out.println("-1");
		
	}
	
	private static void bfs () {
		
		while (!q.isEmpty()) {
			Pair cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
				
				//토마토가 익지 않았고, 한 번도 방문된 적 없을 때 토마토를 익히고 이 지점까지 몇 초만에 방문했는지 기록
				
				if (box[ny][nx]==RAW && dist[ny][nx]==-1) { 
					dist[ny][nx]=dist[y][x]+1;
					cnt--; 									//익지 않은 토마토 개수 줄여줌
					latest=dist[ny][nx];					//가장 최근에 토마토가 익은 시간 갱신
					q.add(new Pair(ny, nx));				//새로 익게 된 토마토 queue에 추가
				}
			}
		}
	}
}
