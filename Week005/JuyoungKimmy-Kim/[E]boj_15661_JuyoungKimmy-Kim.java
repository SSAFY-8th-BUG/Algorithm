package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15661 {

	static int N, ans=Integer.MAX_VALUE;
	static int [][]S;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		S=new int[N+1][N+1];
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=1; j<=N; j++) {
				S[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb (0,1,0,false);
		System.out.println(ans);
	}
	
	static void comb (int tgtIdx, int srcIdx, int mask, boolean changed) {
		
		if (mask!=0 && changed) {
			calc (mask);
			if (tgtIdx==N) return;
		}
		
		if (srcIdx==N+1) return;
		
		comb (tgtIdx+1, srcIdx+1, mask | 1<<srcIdx, true);
		comb (tgtIdx, srcIdx+1, mask, false);
	}
	
	static void calc (int mask) {
		int sSum=0, lSum=0;
		List<Integer> start, link;
		start=new ArrayList<> ();
		link=new ArrayList<> ();
		
		for (int i=1; i<=N; i++) {
			//선택 되었다면
			if ((mask & 1<<i)!=0) 
				start.add(i);
			//선택되지 않았다면
			else link.add(i);
		}
		
		
		//start team 점수 계산
		for (int i=0; i<start.size(); i++) {
			for (int j=i+1; j<start.size(); j++) {
				sSum+=S[start.get(i)][start.get(j)];
				sSum+=S[start.get(j)][start.get(i)];
			}
		}
		
		//link team 점수 계산
		for (int i=0; i<link.size(); i++) {
			for (int j=i+1; j<link.size(); j++) {
				lSum+=S[link.get(i)][link.get(j)];
				lSum+=S[link.get(j)][link.get(i)];
			}
		}
		
		int diff=Math.abs(sSum-lSum);
		ans=Math.min(diff, ans);
	}

}
