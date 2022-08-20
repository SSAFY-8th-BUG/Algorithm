package backtracking;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * 중복 조합
 * IXXX == XXXI 결과 값이 같기 때문에 중복은 허용하되, 순서는 신경쓰지 않는 중복 조합
 */

public class BOJ16922 {

	static int N, ans;
	static int[] tgt;
	static int[] src= {1,5,10,50};
	static boolean[] selected;
	
	static Set<Integer> set=new HashSet<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		tgt=new int[N];
		selected=new boolean[N];
		
		dfs(0,0);
		System.out.println(set.size());
	}
	
	private static void dfs (int tgtIdx, int srcIdx) {
		if (tgtIdx==N) {
			
			int sum=0;
			for (int i=0; i<N; i++)
				sum+=tgt[i];
			
			set.add(sum);
			
			return ;
		}
		
		for (int i=srcIdx; i<4; i++) {
			tgt[tgtIdx]=src[i];
			dfs (tgtIdx+1, i);
		}
	}
}
