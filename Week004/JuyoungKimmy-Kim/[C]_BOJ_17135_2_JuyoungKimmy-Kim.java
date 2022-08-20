package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class ENEMY_ {
	int y; int x;
	int dist;
	
	ENEMY_ (int y, int x, int dist) {
		this.y=y;
		this.x=x;
		this.dist=dist;
	}
} 

public class BOJ17135_2 {

	static int N, M, D, K, ans;
	static int remain, killed;		//remain = 현재 남아 있는 수, killed = 궁수에 의해 죽은 적
	static int[][] map;
	static int[] tgt=new int[3];	//궁수 위치 저장할 배열 -> 3명
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==1) K++;
			}
		}
		
		comb (0,0);
		System.out.println(ans);
	}
	
	static void comb (int srcIdx, int tgtIdx) {
		if (tgtIdx==3) {
			
			int[][] tmp=new int[N][M];
			copyMap (map, tmp);
			
			remain=K;
			killed=0;
			
			while (true) {
				shooting();
				move();
				
				if (remain==0) break;
			}
			
			ans=Math.max(ans, killed);
			
			copyMap (tmp, map);
			return ;
		}
		
		if (srcIdx==M) return ;
		
		tgt[tgtIdx]=srcIdx;
		comb (srcIdx+1, tgtIdx+1);		//srcIdx 자리 선택 X
		comb (srcIdx+1, tgtIdx);		//scrIdx 자리 선택
	}
	
	static void shooting () {
		
		List <int[]> kill=new ArrayList<>();	 		//궁수 3명이 최종적으로 죽일 적 좌표
		for (int k=0; k<3; k++) {
			int y=N; int x=tgt[k];
			
			List<ENEMY_> canKill=new ArrayList<>();		//k 번째 궁수가 죽일 수 있는 적 모음
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[i][j]==1) {
						int dist=Math.abs(y-i) +Math.abs(x-j);
						if (dist<=D) {
							canKill.add(new ENEMY_ (i,j,dist));
						}
					}
				}
			}
			
			if (canKill.size()==0) continue;
			
			Collections.sort(canKill, (ENEMY_ e1, ENEMY_ e2)-> 
			e1.dist==e2.dist ? e1.x-e2.x : e1.dist-e2.dist);

			kill.add(new int[] {canKill.get(0).y, canKill.get(0).x});
		}
		
		for (int k=0; k<kill.size(); k++) {
			int y=kill.get(k)[0];
			int x=kill.get(k)[1];
			
			if (map[y][x]==0) continue;
			map[y][x]=0; 
			
			remain--; killed++;
		}
	}
	
	static void move () {
		for (int i=N-1; i>=0; i--) {
			for (int j=0; j<M; j++) {
				
				int ny=i+1;
				if (map[i][j]==1 && ny==N) {
					map[i][j]=0;
					remain--;
				}
				else if (map[i][j]==1) {
					map[i][j]=0;
					map[ny][j]=1;
				}
				
			}
		}
	}
	
	// a의 맵을 b에 복사
	static void copyMap (int [][]a, int [][]b) {
		for (int i=0; i<N; i++)
			for (int j=0; j<M; j++) 
				b[i][j]=a[i][j];
	}
}
