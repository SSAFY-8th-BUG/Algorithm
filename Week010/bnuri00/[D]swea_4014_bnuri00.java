package week010;

import java.io.*;
import java.util.*;

/* 
 * swea 4014 활주로 건설
 * 
 * 풀이시간: 1시간 30분?
 * 
 * <풀이>
 * - 활주로는 세로 또는 가로방향으로 설치할 수 있음
 * 
 * <삽질목록>
 * - 오목, 볼록 고려 안해서 꼬임ㅎㅎ;
 * */

public class Dswea_4014_bnuri00 {	
	static int N, X;	// 지도 크기, 경사로 길이
	
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 가로방향 (row)
			int rowRoad = makeRowRoad();
			
			// 세로방향 (col)
			int colRoad = makeColRoad();
			
			System.out.println("#"+t+" " + (rowRoad + colRoad));
		}
		

	}
	
	private static boolean canMakeRoad(int[] line) {
		
		int height = line[0];
		int length = 1;
		
		boolean isFirst = true;
		boolean isUp = false;	// 올라가면 true, 내려가면 false
		
		for (int i = 1; i < N; i++) {

			if(line[i] == height) {	// 동일한 높이 유지
				length++;
			} else {	// 높이 달라짐
				
				// 높이차가 1 이상이면 경사로 못만듦
				if(Math.abs(line[i]-height) > 1) return false;
				
				if(isFirst) {	// 맨 앞임
					if(line[i] > height) {	// 올라감
						// 시작 높이에서 다음 높이가 올라가는 방향, 경사로 길이가 충분하지 않을 경우
						if(length < X) return false;
						isUp = true;
						
					} else isUp = false;	// 내려감
					
					isFirst = false;
				}
				else if(line[i] > height) {	// 올라감
					if(isUp) {
						// 계속 올라가는 방향
						if(length < X) return false;
					} else {
						// 내려갔다 올라감 (오목)
						if(length < X*2) return false;
					}		
					isUp = true;
				}
				else {	// 내려감
					if(isUp) {
						// 올라갔다 내려감 (볼록)
						// do nothing
					} else {
						// 계속 내려감
						if(length < X) return false;
					}
					
					
					isUp = false;
				}
				
				// 별 문제 없으면
				// 높이 바꿔주고 길이 1로
				length = 1;
				height = line[i];
			}
		}
		
		if(!isUp && length < X) return false;
		
		return true;
	}

	private static int makeColRoad() {
		
		int roadNum = 0;
		int[] col = new int[N];
		
		for (int j = 0; j < N; j++) {
			
			for (int i = 0; i < N; i++) {
				col[i] = map[i][j];
			}
			
			if(canMakeRoad(col)) roadNum++;	
		}
		return roadNum;		
	}
	

	private static int makeRowRoad() {
		int roadNum = 0;		
		for (int i = 0; i < N; i++) {
			if(canMakeRoad(map[i])) roadNum++;
		}
		
		return roadNum;
	}

}
