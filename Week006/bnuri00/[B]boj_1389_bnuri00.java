package week006;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 40분
 * 참고: boj 알고리즘 분류
 * 
 * 14272KB	132ms
 * 
 * <풀이방식>
 * - bfs, 인접리스트
 * 
 * */
public class Bboj_1389_bnuri00 {
	static int N, M;
	static List[] friends;

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		friends = new List[N+1];
		for (int i = 1; i <= N; i++) {
			friends[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 쌍방 친구 추가
			friends[a].add(b);
			friends[b].add(a);
		}
		
		int min = Integer.MAX_VALUE;
		int person = N+1;
		for (int i = 1; i <= N; i++) {
			int num = kevinNumCalc(i);	// 케빈베이컨 수 계산
			
			if(min > num) {	// 케빈베이컨 수가 더 작은거 나옴
				min = num;
				person = i;
			}else if(min == num && person > i) {	// 같은 케빈베이컨수, 더 적은 person 나옴
				person = i;	
			}
		}
		System.out.println(person);
		
		
	}
	// 케빈 베이컨수 계산 함수
	static int kevinNumCalc(int me) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] checked = new boolean[N+1];
		
		int len = 0;	// 처음은 본인에서 시작 -> 친구길이 0
		int total = 0;
		checked[me] = true;
		q.add(me);
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			// 큐 사이즈만큼 반복
			for (int i = 0; i < size; i++) {
				int n = q.poll();
				
				total += len;	// 케빈베이컨 수
				
				// 친구 리스트에서 체크 안한 친구이면 큐에 넣음
				friends[n].forEach((e) -> {
					if(!checked[(int) e]) {
						q.add((Integer)e); 
						checked[(int)e] = true;
					}
				});
			}
			len++;	// 다음 단계 친구 볼 것이므로 길이 늘리기
		}
		
		return total;
	}

}
