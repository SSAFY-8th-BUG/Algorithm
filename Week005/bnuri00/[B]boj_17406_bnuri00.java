package week005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 
 * 풀이시간: 2시간?
 * 참고: boj 질문(문제 잘못 읽은거 찾음)
 * 
 * 26108KB	308ms
 * 
 * <풀이방식>
 * - 원본 배열로 풀이에 쓰는 배열 사용 전 리셋
 * - Operation 클래스 만들어 연산 저장
 * - 연산 순서 바뀌면 결과 바뀜, 따라서 모든 경우로 (순열이용) 시도
 *  
 * 
 * <실수한거>
 * - 문제 잘 안읽음ㅜ
 * 
 * */
public class Bboj_17406_배열돌리기4 {
	
	static class Operation{
		int y, x, size;

		public Operation(int y, int x, int size) {
			super();
			this.y = y;
			this.x = x;
			this.size = size;
		}
		
	}
	
	static int N, M, K, result = Integer.MAX_VALUE;;
	static int[][] map, originMap;
	
	static Operation[] operArr;
	static int[] seqArr;
	static boolean[] select;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		originMap = new int[N+1][M+1];	// 0: dummy
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 연산 입력받기
		operArr = new Operation[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			operArr[i] = new Operation(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		// 순열에 사용할 배열들
		seqArr = new int[K];
		select = new boolean[K];
		
		perm(0);
			
		System.out.println(result);

	}
	static void perm(int tgtIdx) {
		if(tgtIdx == K) {
			// complete code
			resetMap();
			
			for (int i = 0; i < K; i++) {
				rotate(operArr[seqArr[i]].y, operArr[seqArr[i]].x, operArr[seqArr[i]].size);
			}
			
			calcMap();
			//print();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if(select[i]) continue;
			
			select[i] = true;
			seqArr[tgtIdx] = i;
			perm(tgtIdx+1);
			
			select[i] = false;
			
		}
		
		
	}
//	static void print() {
//		System.out.println("*******************************");
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}
	static void resetMap() {
		for(int i = 1; i <= N; i++) {
			map[i] = originMap[i].clone();
		}
	}
	
	static void calcMap() {
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += map[i][j];
			}
			result = Math.min(result, sum);
		}
		
	}
	
	static void rotate(int y, int x, int size) {
		
		while(size>0) {
			// 왼쪽 위 지점
			int sy = y-size;
			int sx = x-size;

			// 오른쪽 아래 지점
			int ey = y+size;
			int ex = x+size;
			
			// 1,1의 데이터
			int tmp = map[sy][sx];
			
			// 움직이며 배열 돌림
			int ny = sy;
			int nx = sx;
			
			// 왼쪽
			while(ny<ey) {
				map[ny][nx] = map[++ny][nx];
			}
			
			// 아래
			while(nx<ex) {
				map[ny][nx] = map[ny][++nx];
			}
			
			// 오른쪽
			while(ny>sy) {
				map[ny][nx] = map[--ny][nx];
			}
			
			// 위
			while(nx>sx+1) {
				map[ny][nx] = map[ny][--nx];
			}
			map[sy][sx+1] = tmp;
			
			
			size--;
				
		}
		
	}

}
