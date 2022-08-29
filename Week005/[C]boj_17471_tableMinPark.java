import java.io.*;
import java.util.*;

// 게리멘더링
public class boj_17471_tableMinPark {
	
	static int N, answer, sum;
	static int[] persons;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		persons = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) persons[i] = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int C = Integer.parseInt(st.nextToken());
			for (int j = 0; j < C; j++) graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		answer = Integer.MAX_VALUE;
		for (int mask = 0; mask < (1 << N); mask++) {				
			int count = 0;
			int aSum = 0, bSum = 0;
			
			sum = 0;
			v = new boolean[N];
			for (int i = 0; i < N; i++) {		
				if ((mask & 1 << i) != 0) v[i] = true;
			}		
			for (int i = 0; i < N; i++) {
				if (v[i]) continue;
				v[i] = true;
				dfs(i);
				count++;
			}
			aSum = sum;

			sum = 0;
			v = new boolean[N];
			for (int i = 0; i < N; i++) {		
				if ((mask & 1 << i) == 0) v[i] = true;
			}		
			for (int i = 0; i < N; i++) {
				if (v[i]) continue;
				v[i] = true;
				dfs(i);
				count++;
			}
			bSum = sum;
			
			
			if (count == 2) answer = Math.min(answer, Math.abs(aSum - bSum));
		}
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);	
		br.close();
	}
	
	static void dfs(int n) {
		v[n] = true;
		sum += persons[n];
		for (int next : graph.get(n)) {
			if (v[next]) continue;
			dfs(next);
		}
	}
}
