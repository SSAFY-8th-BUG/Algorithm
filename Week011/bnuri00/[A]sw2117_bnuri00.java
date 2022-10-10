package week011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 홈방범서비스
 * 
 * 풀이시간: 1시간 30분
 * 참고: X
 * 
 * <풀이방식>
 * - 이분탐색
 * 
 * - 무선충전 비슷하게 풀었음
 * - 집 위치(y,x) 리스트에 저장
 * 
 * */

public class sw2117_bnuri00 {
	static int N, M;
	static ArrayList<int[]> homeList = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 이전테케 썼던거 초기화
			homeList.clear();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 집 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if(st.nextToken().charAt(0) == '1') {
						homeList.add(new int[] {i, j});
					}
				}
			}
			sb.append("#").append(t).append(" ").append(homeService()).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int homeService() {
		int homeNum = 0;
		
		int right = N%2==1? N:N+1;	// 최대는 N*N 도시가 모두 서비스 제공이 가능한 값으로 설정
		int left = 1;
		int mid;
		
		while(left <= right) {
			mid = (left+right)/2;
			
			int curr = provide(mid);
			if(curr == -1) {
				right = mid-1;
			}else {
				left = mid+1;
				homeNum = curr;
			}
		}
	
		return homeNum;
	}
	private static int provide(int mid) {
		int num = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int currNum = _provide(i, j, mid);
				num = Math.max(num, currNum);
			}
		}
		
		int operCost = mid*mid + (mid-1)*(mid-1);
		int cost = num * M;
		
		// 손해가 나면 -1 리턴
		// 손해 나지 않으면 집 개수 리턴
		return cost-operCost >= 0? num: -1;
	}
	private static int _provide(int i, int j, int mid) {
		int num = 0;
		
		// 모든 집에 대해서 범위 내부인지 검사
		// 범위 내부인 집 개수 세기 
		for (int[] home : homeList) {
			if(Math.abs(home[0]-i) + Math.abs(home[1]-j) <= mid-1)
				num++;
		}
		return num;
	}


}
