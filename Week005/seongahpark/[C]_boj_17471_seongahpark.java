package study;
import java.util.*;
import java.io.*;

/*
1. 1~N의 부분집합을 모두 구함
2. 부분집합에 '포함된 팀' vs '포함되지 않은 팀'으로 2개의 팀으로 나누어 각각의 팀이 연결되어있는지 확인
3. 두 팀이 유효하면, 각 팀의 점수 차이를 구하여 비교 후 갱신
*/

public class Main {
	static int N, res = Integer.MAX_VALUE;
	static int [][] arr;
	static int [] people;
	static boolean select[];
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
			
		arr = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); 
			for (int j = 0; j < M; j++)
				arr[i][Integer.parseInt(st.nextToken())] = 1;
		}
 
		// 2개 지역구 부분집합
		select = new boolean[N + 1];
		subset(1);
		
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}
 
	// 부분집합
	private static void subset(int cnt) {
		// 기저 조건
		if (cnt == N + 1) {
			// 두 팀이 각각 연결되어있는지 확인
			if (check(true) && check(false))
				res = Math.min(res, gap());
			return;
		}

		select[cnt] = true;
		subset(cnt + 1); 
 
		select[cnt] = false;
		subset(cnt + 1);
	}
 
	// 팀이 연결되어있는지 체크
	private static boolean check(boolean flag) {
		boolean visited[] = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
 
		// 시작점 찾기
		for (int i = 1; i <= N; i++) {
			if (select[i] == flag) {
				q.add(i);
				visited[i] = true;
				break;
			}
		}

		while (!q.isEmpty()) {
			int n = q.poll();
			
			for (int i = 1; i <= N; i++) {
				// 시작점 or flag 다른 팀 -> 스킵
				if (visited[i] || flag != select[i])
					continue;
 
				if (arr[n][i] == 1) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
 
		// 같은 flag팀인데 방문 안 한 곳이 있는지 확인
		for (int i = 1; i <= N; i++) {
			if (select[i] == flag && !visited[i]) // 모든 정점이 연결되지 않았다면
				return false;
		}
 
		return true;
	}
	
	// 두 팀의 점수 차
	private static int gap() {
		int teamA = 0, teamB = 0;
 
		for (int i = 1; i <= N; i++) {
			if (select[i])	// 부분집합에 포함된 팀
				teamA += people[i];
			else // 미 포함 팀
				teamB += people[i];
		}
 
		return Math.abs(teamA - teamB);
	}
 
}
