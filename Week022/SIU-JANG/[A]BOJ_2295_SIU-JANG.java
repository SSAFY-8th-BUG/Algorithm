package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_세수의합_2295 {
	
	static int N;
	static int[] arr;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// Add x + y
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				list.add(arr[i] + arr[j]);
			}
		}
		
		// Sort list
		Collections.sort(list);
		
		// if arr[k] - arr[z] in x + y list then return
		for (int k = N - 1; k >= 0; k--) {
			for (int z = k - 1; z >= 0; z--) {
				int target = arr[k] - arr[z];
				
				if (Collections.binarySearch(list, target) > -1) {
					System.out.println(arr[k]);
					return;
				}
			}
		}
	}
}
