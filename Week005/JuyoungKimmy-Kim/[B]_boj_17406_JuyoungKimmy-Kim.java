package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class RCS {
	int r,c,s;

	RCS (int r, int c, int s) {
		this.r=r;
		this.c=c;
		this.s=s;
	}
}

public class BOJ17406 {

	static int N,M,K, ans=Integer.MAX_VALUE;
	static int[][] map;
	static RCS[] rcs;
	static int[] tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		rcs=new RCS[K];
		tgt=new int[K];
		
		map=new int[N][M];
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			
			rcs[i]=new RCS(r-1,c-1,s);
		}
		
		perm (0,0);
		System.out.println(ans);
	}
	
	static void perm (int tgtIdx, int mask) {
		if (tgtIdx==K) {
			
			int[][] cmap=new int[N][M];
			copyMap (map, cmap);
			
			for (int i=0; i<K; i++)
				rotate (tgt[i]);
			
			check();
			
			copyMap (cmap, map);
			
			return ;
		}
		
		for (int i=0; i<K; i++) {
			if ((mask & 1<<i)!=0) continue;
			tgt[tgtIdx]=i;
			perm (tgtIdx+1, mask | 1<<i);
		}
	}
	
	static void rotate (int idx) {
		int sy=(rcs[idx].r)-(rcs[idx].s);
		int sx=(rcs[idx].c)-(rcs[idx].s);
		int ey=(rcs[idx].r)+(rcs[idx].s);
		int ex=(rcs[idx].c)+(rcs[idx].s);
		
		while (sy!=ey) {
			
			int temp=map[sy][sx];
			
			//아래에서 위로
			for (int i=sy+1; i<=ey; i++)
				map[i-1][sx]=map[i][sx];
			
			//오른쪽에서 왼쪽으로
			for (int i=sx+1; i<=ex; i++)
				map[ey][i-1]=map[ey][i];
			
			//위에서 아래로
			for (int i=ey-1; i>=sy; i--)
				map[i+1][ex]=map[i][ex];
			
			//왼쪽에서 오른쪽으로
			for (int i=ex-1; i>=sx; i--) 
				map[sy][i+1]=map[sy][i];
			
			map[sy][sx+1]=temp;
			
			sy+=1; sx+=1;
			ey-=1; ex-=1;
			
			//print();
		}
	}
	
	static void check () {
		for (int i=0; i<N; i++) {
			int sum=0;
			for (int j=0; j<M; j++) {
				sum+=map[i][j];
			}
			
			if (ans>sum) ans=sum;
		}
	}
	
	static void copyMap (int [][]a, int [][]b) {
		for (int i=0; i<N; i++) 
			for (int j=0; j<M; j++)
				b[i][j]=a[i][j];
	}
	
	static void print () {
		System.out.println("===============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
