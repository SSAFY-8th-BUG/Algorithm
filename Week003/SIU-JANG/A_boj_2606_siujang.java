package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_바이러스_2606 {
	
	static int ComCnt, P, ans;
	static int[][] graph;
	static boolean[] checked;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		ComCnt = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		graph = new int[ComCnt + 1][ComCnt + 1];
		checked = new boolean[ComCnt + 1];
		q = new ArrayDeque<>();
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			graph[n1][n2] = 1;
			graph[n2][n1] = 1;
		}
		
		bfs();
		
		for (int i = 2; i <= ComCnt; i++) {
			if (checked[i]) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs() {
		q.add(1);
		checked[1] = true;
		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			for (int i = 0; i < currentSize; i++) {
				int current = q.poll();
				for (int j = 0; j <= ComCnt; j++) {
					if (graph[current][j] == 1 && !checked[j]) {
						checked[j] = true;
						q.add(j);
					}
				}
			}
		}
	}
}
