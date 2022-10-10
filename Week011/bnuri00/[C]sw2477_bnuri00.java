package week011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw2477_bnuri00 {
	static class Customer{
		int n, time;
		boolean deskA;	// 접수창구 A 방문
		
		public Customer(int n, int time) {
			super();
			this.n = n;
			this.time = time;
		}
	}
	static BufferedReader br;
	
	static int N, M, K, A, B;	// 접수창구 수 N, 정비창구 수 M, 고객 수 K, 찾는 접수창구 A, 찾는 정비창구 B 
	static ArrayList<Integer> resultList = new ArrayList<>();	// 접수창구 A, 정비창구 B 방문한 고객 리스트
	static int[] customerTimes;	// 고객 들어오는 시간 입력받는 배열
	
	static int[] receptTimes;
	static int[] repairTimes;
	
	static Customer[] receptDesk;	// 접수창구
	static Customer[] repairDesk;	// 정비창구
	
	static Queue<Customer> receptQ = new ArrayDeque<>();	// 접수 창구 대기열
	static Queue<Customer> repairQ = new ArrayDeque<>();	// 정비 창구 대기열
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			input();	// 입력받음, 배열 다시 만들기
			
			// 맨앞에 고객 넣기
			// 남은 시간 넣기
			// 찾는 접수창구면 deskA를 true로
			receptDesk[1] = new Customer(1, receptTimes[1]);
			if(A==1) receptDesk[1].deskA = true;
			
			int custN = 2;
			int time = customerTimes[1];	// 처음 고객 도착시간
			
			// 2번 ~ K번 고객 받기
			while(custN <= K) {
				// 이번에 들어올 고객이 올 때까지 걸리는 시간: 들어올 고객 시간 - 현재 시간
				int goTime = customerTimes[custN] - time;
				
				// 지나야 할 시간동안 창구, 대기열 처리
				for (int i = 0; i < goTime; i++) {
					move();
				}
				time = customerTimes[custN];	// 시간 넘기기
				
				// 접수 창구 빈 곳중 맨 앞 찾기
				int deskIdx = getEmptyDesk(receptDesk);
				
				// 접수창구 꽉찼거나 대기열에 사람 있음
				if(deskIdx == -1||!receptQ.isEmpty()) receptQ.add(new Customer(custN, 0));
				else {	// 접수창고 빈 곳 있고 대기열에 사람 X
					receptDesk[deskIdx] = new Customer(custN, receptTimes[deskIdx]);
					if(deskIdx == A) receptDesk[deskIdx].deskA = true;
				}
				
				// 다음고객으로
				custN++;
				
			}
			
			// 남아있는 고객 처리
			boolean repairEmpty;
			boolean receptEmpty;
			while(true) {
				repairEmpty = true;
				receptEmpty = true;
				for (int i = 1; i <= M; i++) {
					if(repairDesk[i] != null) {
						repairEmpty = false;
						break;
					}
				}
				for (int i = 1; i <= N; i++) {
					if(receptDesk[i] != null) {
						receptEmpty = false;
						break;
					}
				}
				
				// 정비창구에 남은 고객 없음
				if(repairEmpty && receptEmpty) break;
				
				move();
				
			}
			
			sb.append("#").append(t).append(" ");
			
			int sum = 0;
			for (int c : resultList) {
				sum += c;
			}
			if(sum == 0) sb.append(-1);
			else sb.append(sum);
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	// 시간 1 지날때마다 동작
	private static void move() {
		boolean isRepairDeskFull = false;
		
		// 정비창구 repairDesk 동작
		// 시간 0이면 빼기
		// 찾던 고객이면 결과리스트에
		for (int i = 1; i <= M; i++) {
			Customer c = repairDesk[i];
			if(c==null) continue;
			
			c.time--;
			
			if(c.time == 0) {
				// B창구, A창구 방문고객이면 결과리스트에..
				if(i == B && c.deskA) resultList.add(c.n);
				repairDesk[i] = null;	// 정비창구에서 삭제
			}
		}
		
		// 정비창구 대기열 repairQ 확인
		// 정비창구에 넣을 수 있으면 넣음
		while(!repairQ.isEmpty()) {
			// 빈 정비창구 번호
			int idx = getEmptyDesk(repairDesk);
			
			// 정비창구 꽉찼음
			if(idx==-1) {
				isRepairDeskFull = true;
				break;
			}
			
			Customer c = repairQ.poll();
			c.time = repairTimes[idx];
			repairDesk[idx] = c;	// 정비창구에 넣기
			
		}
		
		// 접수창구 receptDesk 동작
		// 시간 0이면 
		// 정비창구 또는 대기열에 넣기
		for (int i = 1; i <= N; i++) {
			Customer c = receptDesk[i];
			if(c==null) continue;
			
			c.time--;
			
			// 처리 끝난 고객
			if(c.time == 0) {
				if(isRepairDeskFull) {	// 정비창구 꽉찼으면 대기열에
					repairQ.add(c);
					receptDesk[i] = null;	// 접수창구에서 삭제
					continue;
				}
				
				// 정비창구에 넣기
				int idx = getEmptyDesk(repairDesk);
				
				// 현재 for 안에서 정비창구 꽉찼을 경우 여기로 들어옴, 한번 들어오고 나서는 이전 FOR문에서 걸러짐
				if(idx == -1) {	
					isRepairDeskFull = true;
					repairQ.add(c);
					receptDesk[i] = null;	// 접수창구에서 삭제
					continue;
				}
				
				c.time = repairTimes[idx];
				repairDesk[idx] = c;
				
				receptDesk[i] = null;	// 접수창구에서 삭제
			}
		}
		
		
		// 접수창구 대기열 확인
		// A 창구면 고객에 표시
		while(!receptQ.isEmpty()) {
			// 빈 정비창구 번호
			int idx = getEmptyDesk(receptDesk);
			if(idx==-1) break;	// 꽉찼음
			
			// 빈 곳 있는 경우
			Customer c = receptQ.poll();
			c.time = receptTimes[idx];
			
			// A창구 들어가는 고객 체크
			if(idx == A) c.deskA = true;
			
			receptDesk[idx] = c;
		}
	}
	
	// 빈 창구 중 첫번째 창구 인덱스 리턴
	// 꽉 찼으면 -1 리턴
	static int getEmptyDesk(Customer[] desk) {
		for (int i = 1; i <= desk.length-1; i++) {
			if(desk[i] == null) return i;
		}
		return -1;
	}

	static void init() {
		// 이전에 쓴거 초기화 작업
		resultList.clear();
		receptQ.clear();
		repairQ.clear();
		
	}
	
	static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		receptTimes = new int[N+1];	// 0: dummy
		for (int i = 1; i <= N; i++) {
			receptTimes[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		repairTimes = new int[M+1];	// 0: dummy
		for (int i = 1; i <= M; i++) {
			repairTimes[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		customerTimes = new int[K+1];
		for (int i = 1; i <= K; i++) {
			customerTimes[i] = Integer.parseInt(st.nextToken());
		}
		
		receptDesk = new Customer[N+1];	// 0: dummy
		repairDesk = new Customer[M+1];	// 0: dummy
		
	}

}
