package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 게리멘더링 풀 때 데인적이 있어서 무난하게 풀었음
 */
public class SWEA2105 {

	static int T,N, ans;
	static int [][] map;
	static boolean[] selected=new boolean[101];
	static Pos[] pos=new Pos[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			ans=-1;
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			simulation();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void simulation () {
		
		/*
		 * 시작점이 될 수 있는 것
		 * y축 : 0~N-3 까지 가능
		 * x축 : 1~N-2 까지 가능
		 * w (왼쪽 아래로 내려가는 길이) : 1~N-1까지 가능
		 * h (오른쪽 아래로 내려가는 길이) : 1~N-1까지 가능
		 * 
		 */
		for (int i=0; i<=N-3; i++) {
			for (int j=1; j<=N-2; j++) {
				for (int w=1; w<=N-1; w++) {
					for (int h=1; h<=N-1; h++) {
						eat (i,j,w,h);
					}
				}
			}
		}
	}
	
	static void eat (int y, int x, int w, int h) {
		
		// 한 번 더 범위 체크
		if (y+w+h>=N || x+h>=N || x-w<0) return ;
		Arrays.fill(selected, false);
		
		//제일 위에부터 반시계 방향으로 좌표를 만들어준다
		
		pos[0]=new Pos (y,x);
		pos[1]=new Pos (y+w, x-w);
		pos[2]=new Pos (y+w+h, x-w+h);
		pos[3]=new Pos (y+h, x+h);
		
		int cnt=0; //먹을 수 있는 디저트 수
		
		// #1. pos[0] -> pos[1]
		
		int ny=y; 
		int nx=x;
		selected[map[ny][nx]]=true;
		cnt++;
		
		for (int i=0; i<w; i++) {
			ny+=1; nx-=1;
			
			if (selected[map[ny][nx]]) return;
			selected[map[ny][nx]]=true;
			cnt++;
		}
		
		// #2.pos[1] -> pos[2]
		
		for (int i=0; i<h; i++) {
			ny+=1; nx+=1;
			
			if (selected[map[ny][nx]]) return;
			selected[map[ny][nx]]=true;
			cnt++;
		}
		
		// #3. pos[2]->pos[3]
		for (int i=0; i<w; i++) {
			ny-=1; nx+=1;
			
			if (selected[map[ny][nx]]) return;
			selected[map[ny][nx]]=true;
			cnt++;
		}
		
		// #4. pos[3]->pos[0] 직전
		for (int i=0; i<h-1; i++) {
			ny-=1; nx-=1;
			
			if (selected[map[ny][nx]]) return;
			selected[map[ny][nx]]=true;
			cnt++;
		}
		
		ans=Math.max(ans, cnt);
		
	}
	
	static class Pos {
		int y,x;
		Pos (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}

}
