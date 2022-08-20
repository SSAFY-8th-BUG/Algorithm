package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

	static final int EMPTY=0;
	static final int WALL=1;
	
	// ㅡ:1, ㅣ:2, \:3 ==> 모양 번호 지정
	
	// d1 {1,0,1}의 의미 -> ㅡ(1)모양을 옆으로 이동하면 ㅡ(1)모양에 위치는 y+=0, x+=1
	// d1 {3,1,1}의 의미 -> ㅡ(1)모양을 45도 회전하면 \(3)모양에 위치는 y+=1, x+=1
	static final int[][] d1= {{1,0,1},{3,1,1}};
	
	// d2 {2,1,0}의 의미 -> |(2)모양을 세로로이동하면 |(2)모양에 위치는 y+=1, x+=0
	// d2 {3,1,1}의 의미 -> |(2)모양을 45도 회전하면 \(3)모양에 위치는 y+=1, x+=1
	static final int[][] d2= {{2,1,0},{3,1,1}};
	
	// d3 {1,0,1}의 의미 -> \(3)모양을 45도 회전+오른쪽 이동하면 ㅡ(1)모양에 위치는 y+=0, x+=1
	// d3 {2,1,0}의 의미 -> \(3)모양을 45도 회전+아래로 이동하면 |(2)모양에 위치는 y+=1, x+=0
	// d3 {3,1,1}의 의미 -> \(3)모양을 45도 대각선아래로 이동하면 \(3)모양에 위치는 y+=1, x+=1
	static final int[][] d3= {{1,0,1},{2,1,0},{3,1,1}};

	
	static int N, ans;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		N=Integer.parseInt(br.readLine());
		map=new int[N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// (1,1), (1,2) 좌표에 ㅡ(1번 모양) 으로 초기값 
		// y,x 는 좌측 하단 좌표 기준
		dfs (1,2,1);
		System.out.println(ans);
	}
	
	private static void dfs (int y, int x, int dir) {
				
		if (y==N && x==N) {
			ans++;
			return ;
		}
		
		int ny=0, nx=0, d=0;
		if (dir==1) {
			for (int i=0; i<2; i++) {
				d=d1[i][0];
				ny=y+d1[i][1];
				nx=x+d1[i][2];
				
				if (canMove(ny, nx, d)) dfs (ny, nx, d);
				
			}
		} else if (dir==2) {
			for (int i=0; i<2; i++) {
				d=d2[i][0];
				ny=y+d2[i][1];
				nx=x+d2[i][2];
				
				if (canMove(ny, nx, d)) dfs (ny, nx, d);
			}
		} else if (dir==3) {
			for (int i=0; i<3; i++) {
				d=d3[i][0];
				ny=y+d3[i][1];
				nx=x+d3[i][2];
				
				if (canMove(ny,nx,d)) dfs(ny, nx, d3[i][0]);
			}
		}
	}
	
	// d==3이라는 뜻은 다음에 움직일 방향이 \ 이기 때문에 2개의 구역을 추가로 더 검사해주어야 함
	private static boolean canMove (int ny, int nx, int d) {
		if (ny<1 || nx<1 || ny>N || nx>N || map[ny][nx]==WALL) return false;
		if (d!=3) return true;
		else if (d==3 && map[ny-1][nx]!=WALL && map[ny][nx-1]!=WALL) return true;
		return false;
	}

}
