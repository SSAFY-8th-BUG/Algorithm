package week004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 보고함ㅜㅜㅜㅜ
 * 
 * 풀이시간: 고민한거 1시간반,,보고한거 30분.,
 * 참고: 블로그 코드..
 * 
 * <풀이>
 * - 색종이 붙일 수 있는지 검사 -> 붙이기 -> 남은 색종이수 줄이기
 * 		dfs() -> 색종이 떼기 -> 남은 색종이수 늘리기
 * - 색종이 검사 / 붙이기 / 떼기 는 시작 x, y 좌표 받아 시작점에서 size 더한만큼 이중 for문으로 순회
 * - dfs는 오른쪽으로 이동하다가 범위 벗어나면 아래로 이동
 * 
 * <삽질목록>
 * - 그냥 못함ㅜ
 * 
 * */
public class D_boj_색종이붙이기 {
	static int result = Integer.MAX_VALUE;
	static int[][] map;	// 색종이 맵
	static int[] remain = {0,5,5,5,5,5};	// 사이즈별 남은 색종이 수, 0: dummy
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		
		// result가 변하지 않았으면 모든 1을 다 덮을 수 없었다..
		if(result==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);	// 일반적인 경우
	}
	
	
	private static void dfs(int x, int y, int count) {
		// 기저조건
		if(x>=10 && y>=9) {
			result = Math.min(result,  count);
			return;
		}
		
		// 가지치기
		if(count >= result) return;
		
		if(x>=10) {	// 옆으로 이동하다 범위 벗어나면 밑으로 이동
			dfs(0,y+1,count);
			return;
		}
		
		// 색종이로 덮을 칸
		if(map[y][x] == 1) {
			for(int i = 5; i >=1; i--) {
				if(checkAttach(x, y, i)&&remain[i]>0) {
					attach(x, y, i);	// 현재 위치에 i 크기 색종이 붙이기
					remain[i]--;	// 붙였으니까 해당 크기 색종이 남은거 줄임
					dfs(x+i, y, count+1);	// 다음에 붙일 곳 찾기, i만큼 색종이로 덮여있으니 x+i부터 검사
					detach(x, y, i);	// 색종이 떼기
					remain[i]++;	// 뗀 색종이 넣음
				}
			}
			
		}else {	// 색종이로 덮을 칸이 아니면 오른쪽으로(x+1)
			dfs(x+1, y, count);
		}
		
	}
	// 색종이 떼기
	private static void detach(int x, int y, int size) {
		for(int i = y; i < y+size; i++) {
			for (int j = x; j < x+size; j++) {
				map[i][j] = 1;	//원상복구
			}
		}
	}
	
	// 색종이 붙이기
	private static void attach(int x, int y, int size) {
		for(int i = y; i < y+size; i++) {
			for (int j = x; j < x+size; j++) {
				map[i][j] = 2;	// 색종이 붙이기
			}
		}
		
	}
	static boolean checkAttach(int x, int y, int size) {
		for(int i = y; i < y+size; i++) {
			for (int j = x; j < x+size; j++) {
				if(i>=10 || j >= 10) return false;	// 범위 벗어남
				if(map[i][j] != 1) return false;	// 색종이 못붙여
			}
		}
		return true;
	}
	

}
