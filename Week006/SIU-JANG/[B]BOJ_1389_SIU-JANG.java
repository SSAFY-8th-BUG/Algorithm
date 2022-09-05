package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드 워셜 알고리즘(O(n^3))
public class BOJ_케빈베이컨의6단계법칙_1389 {
	
	static int N, M, a, b, ans, min = Integer.MAX_VALUE;
	static int[][] matrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		matrix = new int[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			matrix[a][b] = 1;
			matrix[b][a] = 1;
		}
		
		// 플로이드 워셜 알고리즘
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) continue;
					if (matrix[i][k] != 0 && matrix[k][j] != 0) {
						if (matrix[i][j] == 0) matrix[i][j] = matrix[i][k] + matrix[k][j];
						else matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);						
					}
				}
			}
		}
		
		// 배열을 순회하면서 케빈 베이컨의 수가 가장 작은 사람을 찾는다.
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				if (matrix[i][j] != Integer.MAX_VALUE) {
					sum += matrix[i][j];					
				}
			}
			if (min > sum) {
				ans = i;
				min = sum;
			}
		}
		
		System.out.println(ans);
	}
}
