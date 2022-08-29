package week005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cboj_17471_게리멘더링 {
	static int N, diff = Integer.MAX_VALUE;
	static int[] people;	// 구역별 인구수
	
	static List<ArrayList<Integer>> graph;
	static boolean[] select;
	static boolean[] visit;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		select = new boolean[N];
		
		// 지역별 인구 수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			people[i] = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // 인접 구역 수
			for (int j = 0; j < cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				graph.get(i).add(n - 1);
			}
		}

		subset(0);
		if (diff == Integer.MAX_VALUE) diff = -1;
		
		System.out.println(diff);
		
	}
	
	static void subset(int tgtIdx) {
		if(tgtIdx == N) {
			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(select[i]) aList.add(i);
				else bList.add(i);
			}
			if(aList.size() == 0 || bList.size() == 0) return;
			
			if(checkPart(aList) && checkPart(bList)) {
				calcDiff();
			}
			return;
		}
		select[tgtIdx] = true;
		subset(tgtIdx+1);
		select[tgtIdx] = false;
		subset(tgtIdx+1);
	}
	private static void calcDiff() {
		int a = 0, b = 0;
		
		for (int i = 0; i < N; i++) {
			if(select[i]) a += people[i];
			else b += people[i];
		}
		diff = Math.min(diff, Math.abs(a-b));
		
	}

	static boolean checkPart(List<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		visit = new boolean[N];
		visit[list.get(0)] = true;
		q.offer(list.get(0));
		
		int count = 1; // 방문한 지역 개수
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int i = 0; i < graph.get(n).size(); i++) {
				int y = graph.get(n).get(i);
				if(list.contains(y) && !visit[y]) { // 선거구에 해당하고, 아직 미방문
					q.offer(y);
					visit[y] = true;
					count++;
				}
			}
		}
		if(count==list.size()) return true;
		
		return false;
	}

}
