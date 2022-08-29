import java.io.*;
import java.util.*;

// 링크와 스타트
public class boj_15661_tableMinPark {
	
	static int N, answer;
	static int[][] cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) cost[i][j] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MAX_VALUE;
		List<Integer> s;
		List<Integer> l;
		for (int mask = 0; mask < 1 << N; mask++) {
			s = new ArrayList<>();
			l = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				if ((mask & 1 << i) == 0) s.add(i);
				else l.add(i);
			}
			
			if (s.size() == 0 || l.size() == 0) continue;
			
			int sSum = calc(s);
			int lSum = calc(l);
			
			answer = Math.min(answer, Math.abs(sSum - lSum));
		}
		
		System.out.println(answer);
		br.close();
	}
	
	static int calc(List<Integer> arr) {
		int sum = 0;
		int size = arr.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				int a = arr.get(i);
				int b = arr.get(j);
				sum += cost[a][b] + cost[b][a];
			}
		}
		return sum;
	}
}
