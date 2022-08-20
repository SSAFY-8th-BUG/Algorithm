package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이거 왜 틀렸는지 모르겠음..
 * 혹시 왜 틀렸는지 알려 줄 사람 찾아요..
 * 
 * 디버깅 하는 것도 너무 힘들고 해서 _2 에서는 예전에 풀었던 거 보고 다시 풀었음..
 */
public class BOJ17136 {

	static int cnt;
	static int ans=Integer.MAX_VALUE;
	static char[][] map=new char[10][10];
	static int[] remain=new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));


		for (int i=0; i<10; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<10; j++) {
				map[i][j]=st.nextToken().charAt(0);
				if (map[i][j]=='1') cnt++;
			}
		}
		Arrays.fill(remain, 5);
		
		dfs(0,0,map);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	
	private static void dfs (int y, int x, char[][] tmp) {
		
		if (cnt==0) {
			int sum=0;
			for (int i=1; i<=5; i++) {
				sum+=(5-remain[i]);
			}
			
			if (sum<ans) ans=sum;
			return ;
		}
		
		for (int i=y; i<10; i++) {
			for (int j=x; j<10; j++) {
				if (tmp[i][j]=='1') {
					
					char[][] cmap=new char[10][10];
					copyMap (tmp,cmap);
					
					for (int s=5; s>=1; s--) {
						if (remain[s]==0) continue;			//색종이 다 섰으면 못 씀
						if (isAvailable (i,j,s,cmap)) {		//해당 위치에 s 크기의 색종이를 놓을 수 있다면
							remain[s]--;					//s 색종이 사용
							cnt-=(s*s);						//1의 개수를 색종이 영역만큼 줄여줌
							dfs (i,0,cmap);					//다음 탐색
							cnt+=(s*s);						//1의 개수 다시 복구
							copyMap (tmp,cmap);				//돌아 왔을 때는 다시 맵 상태 복구
						}
					}
				}
			}
		}
	}
	
	private static boolean isAvailable (int y, int x, int size, char[][] cmap) {
		for (int i=y; i<y+size; i++) {
			for (int j=x; j<x+size; j++) {
				if (i>=10 || j>=10 || cmap[i][j]!='1')
					return false;
			}
		}
		
		// 색종이를 붙일 수 있다면 맵의 상태를 바꿔줌
		for (int i=y; i<y+size; i++) {
			for (int j=x; j<x+size; j++) {
				cmap[i][j]='2';
			}
		}
		return true;
	}
	
	private static void copyMap (char[][] a, char [][] b) {
		for (int i=0; i<10; i++)
			for (int j=0; j<10; j++)
				b[i][j]=a[i][j];
	}

}
