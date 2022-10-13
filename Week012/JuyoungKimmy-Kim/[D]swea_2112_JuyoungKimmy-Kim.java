package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA2112_2 {

	static int T, D, W, K, ans;
	static boolean done=false;
	
	static int [][] map;
	static boolean []selected;
	
	static List<Integer> tgt;
	static Queue<Integer> queue=new ArrayDeque<>();;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			map=new int[D][W];
			selected=new boolean[D];
			
			for (int i=0; i<D; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			ans=Integer.MAX_VALUE;
			done=false;
			if (!check () && K!=1) 
				comb (0,0,true);
			if (ans==Integer.MAX_VALUE) ans=0;

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void comb (int tgtIdx, int  cnt, boolean flag) {
		if (cnt>=ans) return ;
		
		if (tgtIdx!=0 && flag) {
			tgt=new ArrayList<>();
			for (int i=0; i<D; i++) {
				if (selected[i])
					tgt.add(i);
			}
			change(0);
		}
		
		if (tgtIdx==D) return ;


		selected[tgtIdx]=true;
		comb (tgtIdx+1, cnt+1,true);
		
		selected[tgtIdx]=false;
		comb (tgtIdx+1, cnt, false);
	}
	
	static void change (int tgtIdx) {
		
		if (tgtIdx==tgt.size()) {
			if (check()) {
				ans=Math.min(ans, tgt.size());
			}
			return ;
		}
		
		//현재 tgt를 모두 A로
		int y=tgt.get(tgtIdx);
		
		for (int j=0; j<W; j++) 
			queue.add(map[y][j]);
			
		for (int j=0; j<W; j++)
			map[y][j]=1;
		
		change (tgtIdx+1);
		
		//현재 tgt를 모두 B로
		for (int j=0; j<W; j++)
			map[y][j]=0;
		
		change (tgtIdx+1);
		
		for (int j=0; j<W; j++) 
			map[y][j]=queue.poll();
	}
	
	static boolean check () {
		for (int j=0; j<W; j++) {
			
			int prev=map[0][j];
			int cnt=1;
			
			for (int i=1; i<D; i++) {
				int now=map[i][j];
				if (prev==now) {
					cnt++;
					if (cnt>=K) break;
				} else {
					prev=now;
					cnt=1;
				}
				
				if (i==D-1) return false;
			}
		}
		return true;
	}
}
