package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class ENEMY {
	boolean alive;
	int no;
	int sy; int sx;
	int y,x;
	int dist;
	
	ENEMY (int no, int y, int x) {
		this.no=no;
		this.y=this.sy=y;
		this.x=this.sx=x;
		alive=true;
	}
} 

public class BOJ17135 {

	static int N,M,D,K;
	static int killed, remain, ans;
	static int[][] map;
	static List<ENEMY> enemy=new ArrayList<>();
	
	static int[] tgt;		//어떤 위치에 궁수가 배치 되었는지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		map=new int[N+1][M];
		tgt=new int[3];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==1) {
					enemy.add(new ENEMY (K++, i,j));
				}
			}
		}
		
		comb (0,0);
		System.out.println(ans);
	}
	
	//궁수의 위치를 tgt 배열에 저장
	private static void comb (int srcIdx, int tgtIdx) {
		if (tgtIdx==3) {
			
			init();
			
			remain=K;			//남아 있는 적은 K명으로 초기화하고 출발
			killed=0; 			//죽인 적은 0명으로 초기화하고 출발
	
			while (true) {
				shooting();
				move();
				
				if (remain==0) break;
			}
			
			if (killed>ans) ans=killed;
			return;
		}
		
		if (srcIdx==M) return;
		
		tgt[tgtIdx]=srcIdx;
		comb (srcIdx+1, tgtIdx+1);
		comb (srcIdx+1, tgtIdx);
	}
	
	private static void shooting () {
		
		/*
		 * 각각의 궁수는 자신의 위치에서 쏠 수 있는 적들을 선발하고
		 * 적이 여러명이 있을 경우 가장 가깝고, 가장 왼쪽에 있는 적을 공격
		 */
		
		int[] kill=new int[3];		// 궁수3명이 최종적으로 죽일 적 저장
		
		for (int i=0; i<3; i++) {
			int y=N; 
			int x=tgt[i];
			
			List<ENEMY> canKill=new ArrayList<ENEMY>(); // i번 궁수가 죽일 수 있는 적들 저장
			for (int j=0; j<K; j++) {
				
				if (!enemy.get(j).alive) continue;
				
				int ey=enemy.get(j).y;
				int ex=enemy.get(j).x;
				int dist=Math.abs(ey-y)+Math.abs(ex-x);
				
				if (dist>D) continue;
				enemy.get(j).dist=dist;
				
				canKill.add(enemy.get(j));			
			}
			
			//Collections.sort(canKill);
			
			if (canKill.size()==0) 
				kill[i]=-1;
			else {
				// 거리가 가깝고, 가장 왼쪽에 있는 순으로 정렬
				Collections.sort(canKill, (ENEMY e1, ENEMY e2)-> e1.dist==e2.dist ?
					e1.x-e2.x : e1.dist-e2.dist);
				
				kill[i]=canKill.get(0).no;
			}
		}
		
		for (int i=0; i<3; i++) {
			
			// i번 째 궁수가 죽일 적이 없거나, 뽑아 놨는데 앞에서 이미 다른 궁수가 죽인 경우
			if (kill[i]==-1 || !enemy.get(kill[i]).alive)
				continue;
			
			enemy.get(kill[i]).alive=false;
			killed++;
			remain--;
		}
	}
	
	/*
	 * 살아있는 모든 적이 아래로 한 칸 이동
	 * 이때 적이 맵의 범위를 벗어나면 죽이고 remain 을 한 개 줄인다
	 * killed는 건드리지 않는다 -> 궁수가 죽인 것이 아니므로
	 */
	private static void move () {
		for (int i=0; i<K; i++) {
			if (!enemy.get(i).alive) continue;
			
			int ny=enemy.get(i).y+1;
			enemy.get(i).y=ny;
			
			if (ny==N) {
				enemy.get(i).alive=false;
				remain--;
			}
		}
	}
	
	private static void init () {
		for (int i=0; i<K; i++) {
			enemy.get(i).alive=true;
			enemy.get(i).y=enemy.get(i).sy;
			enemy.get(i).x=enemy.get(i).sx;
			enemy.get(i).dist=0;
		}
	}
}
