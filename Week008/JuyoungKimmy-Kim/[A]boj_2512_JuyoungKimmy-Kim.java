package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {
	
	static int N,M,total,ans;
	static int[] budget;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		budget=new int[N];

		st=new StringTokenizer (br.readLine());
		
		int low=1; int high=-1;
		for (int i=0; i<N; i++) {
			budget[i]=Integer.parseInt(st.nextToken());
			high=Math.max(high, budget[i]);
		}
		
		Arrays.sort(budget);
		
		M=Integer.parseInt(br.readLine());
		
		while (low<=high) {
			int mid=(low+high)/2;
			if (allocate (mid)) {
				low=mid+1;
			}
			else high=mid-1;
		}
		System.out.println(high);
	}
	
	private static boolean allocate (int limit) {
		
		long sum=0;
		for (int i=0; i<N; i++) {
			if (budget[i]<=limit) 
				sum+=budget[i];
			else sum+=limit;
			
			if (sum>M) return false;
		}
		
		return true;
	}

}
