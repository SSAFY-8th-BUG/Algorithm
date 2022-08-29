import java.util.*;
import java.io.*;

public class Main {

	static int [][] map, backup, rcs;
	static int N, M, K, min;
	
	static int [] tgt;
	static boolean [] select;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		backup = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = backup[i][j] = n;
			}
		}
		
		// 초기화
		min = Integer.MAX_VALUE;
		rcs = new int[K][3];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			rcs[i][0] = Integer.parseInt(st.nextToken());
			rcs[i][1] = Integer.parseInt(st.nextToken());
			rcs[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 순열
		tgt = new int[K];
		select = new boolean[K];
		
		perm(0);
		
		System.out.println(min);
	}
	
	static void perm(int tgtIdx) {
		// 기저 조건
		if(tgtIdx == K) {
			// 회전 - 최소값 갱신 - 배열 초기화
			rotate();
			
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<M; j++) {
					sum += map[i][j];
				}
				min = Math.min(min,  sum);
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = backup[i][j];
				}
			}
			
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(select[i]) continue;
			tgt[tgtIdx] = i;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false; 
		}
	}
	
	
	static void rotate() {
		for(int k=0; k<K; k++) {
			int n = tgt[k];
	        int r = rcs[n][0]-1;
	        int c = rcs[n][1]-1;
	        int s = rcs[n][2];
	        
	        int sy = r-s;
	        int ey = r+s;
	        int sx = c-s; 
	        int ex = c+s;
			
	        while( true ) {
	            if( s == 0 ) break;
	            
	            int temp = map[sy][sx]; // 백업 (왼쪽 위)
	            
	            // 왼쪽 위로 1칸 이동
	            for(int i = sy; i < ey; i++) {
	                map[i][sx] = map[i+1][sx];
	            }
	            
	            // 아래 왼쪽 1칸 이동
	            for(int i = sx; i < ex; i++) {
	                map[ey][i] = map[ey][i+1];
	            }
	            
	            // 오른쪽 아래로 1칸 이동
	            for(int i = ey; i > sy; i--) {
	                map[i][ex] = map[i-1][ex];
	            }
	            
	            // 위 오른쪽으로 한칸 이동
	            for(int i = ex; i > sx; i--) {
	                map[sy][i] = map[sy][i-1];
	            }
	            
	            // temp 로부터 이동 마지막 부분 복사( 왼쪽 위의 한칸 오른쪽 )
	            map[sy][sx+1] = temp;
	            
	            sy += 1; 
	            sx += 1; 
	            ey -= 1;
	            ex -= 1;
	            
	            s--;
	        }
		}

	}

}