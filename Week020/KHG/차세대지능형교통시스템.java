import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;



public class Main {

	static int N,T,NN;
	static int[][][] crossInfos;
	static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] dirs2 = {{2,0},{1,1},{0,2},{-1,1},{-2,0},{-1,-1},{0,-2},{1,-1}};
	static int[][] sinhoInfos = {{7,0,1},{5,6,7},{3,4,5},{1,2,3},{7,0},{5,6},{3,4},{1,2},{0,1},{6,7},{4,5},{2,3}};
	static int[][] nextDir = {{3,0,1},{2,3,0},{1,2,3},{0,1,2},{3,0},{2,3},{1,2},{0,1},{0,1},{3,0},{2,3},{1,2}};
	static boolean[][] visited;
	static boolean[][][] visited2;
	static int answer;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N=Integer.parseInt(stn.nextToken());
		NN=N*N;
		crossInfos = new int[2*N-1][2*N-1][4];
		T=Integer.parseInt(stn.nextToken());
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				stn = new StringTokenizer(br.readLine());	
				for(int i=0; i<4; i++) {
					crossInfos[2*y][2*x][i] = Integer.parseInt(stn.nextToken())-1;
				}
			}
		}
		

		visited = new boolean[2*N-1][2*N-1];
		visited2 = new boolean[2*N-1][2*N-1][4];
		answer=0;
		go(0,1,3,0);
		System.out.println(answer);
		/*for(int y=0; y<2*N-1; y++) {
			System.out.println(Arrays.toString(visited[y]));
		}*/
				
	}
	
	static void go(int x, int y, int dir, int time) {
		if(time>T)return;
		int t = time%4;
		int cx = x+dirs[dir][0];
		int cy = y+dirs[dir][1];
		int sinho = crossInfos[cy][cx][t];
		if(dir==0 && sinho%4!=0)return;
		else if(dir==1 && sinho%4!=3)return;
		else if(dir==2 && sinho%4!=2)return;
		else if(dir==3 && sinho%4!=1)return;
		if(!visited[cy][cx] ) {
			visited[cy][cx]=true;
			answer++;
		}
		int it = 2;
		if(sinho<4)it=3;
		
		for(int i=0; i<it; i++) {
			int nx = x + dirs2[sinhoInfos[sinho][i]][0];
			int ny = y + dirs2[sinhoInfos[sinho][i]][1];
			int ndir = nextDir[sinho][i]; 
			if(nx<0 || ny<0 || nx>=2*N-1 || ny>=2*N-1) continue;
			
			go(nx,ny, ndir, time+1);
		}
	}
	
	
		
}