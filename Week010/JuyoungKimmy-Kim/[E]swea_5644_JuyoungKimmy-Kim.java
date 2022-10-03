package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5644_5 {

	static final int dy[] = {0,-1,0,1,0};
	static final int dx[] = {0,0,1,0,-1};
	
	static int T,M,A, ans,ay,ax,by,bx;
	static int[][] move;
	static List<BC> bcList;
	static List<Integer>[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			M=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			
			move=new int[2][M+1];
			
			for (int i=0; i<2; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=1; j<=M; j++) {
					move[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			bcList=new ArrayList<>();
			for (int i=0; i<A; i++) {
				st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken());
				
				bcList.add(new BC (x,y,c,p));
			}
			
			map=new ArrayList[11][11];
			for (int i=0; i<=10; i++) {
				for (int j=0; j<=10; j++) {
					map[i][j]=new ArrayList<>();
				}
			}
			
			for (int i=0; i<bcList.size(); i++) {
				bfs (i);
			}
			
			ay=ax=1;
			by=bx=10;
			
			ans=0;
			charge();
			System.out.println("#"+tc+" "+ans);
						
		}
	}
	
	
	static void charge () {

		for (int T=0; T<=M; T++) {
			int aDir=move[0][T];
			int bDir=move[1][T];
			
			ay+=dy[aDir]; ax+=dx[aDir];
			by+=dy[bDir]; bx+=dx[bDir];
			
			int aSize=map[ay][ax].size();
			int bSize=map[by][bx].size();
			
			if (aSize==0 && bSize==0) continue;
			else if (aSize>0 && bSize==0) {
				int max=0;
				for (int i=0; i<aSize; i++) {
					max=Math.max(max, bcList.get(map[ay][ax].get(i)).p);
				}
				ans+=max;
			} else if (aSize==0 && bSize>0) {
				int max=0;
				for (int i=0; i<bSize; i++) {
					max=Math.max(max, bcList.get(map[by][bx].get(i)).p);
				}
				ans+=max;
			} else  {
				int max=0;
				for (int i=0; i<aSize; i++) {
					for (int j=0; j<bSize; j++) {
						int aIdx=map[ay][ax].get(i);
						int bIdx=map[by][bx].get(j);
						
						if (aIdx==bIdx) {
							max=Math.max(max, bcList.get(aIdx).p);
						} else {
							max=Math.max(max, bcList.get(aIdx).p + bcList.get(bIdx).p);
						}
					}
				}
				ans+=max;
			}
		}		
	}

	static void bfs (int n) {
		Queue<int[]> q=new ArrayDeque<> ();
		
		int [][] dist=new int[11][11];
		for (int i=0; i<=10; i++)
			Arrays.fill(dist[i], -1);
		
		int y=bcList.get(n).y;
		int x=bcList.get(n).x;
		int C=bcList.get(n).c;
		
		q.add(new int [] {y,x});
		map[y][x].add(n);
		dist[y][x]=0;
		
		while (!q.isEmpty()) {
			int cy=q.peek()[0];
			int cx=q.poll()[1];
			
			for (int d=1; d<=4; d++) {
				int ny=cy+dy[d];
				int nx=cx+dx[d];
				
				if (ny>=1 && nx>=1 && ny<=10 && nx<=10 && dist[ny][nx]==-1) {
					dist[ny][nx]=dist[cy][cx]+1;
					
					if (dist[ny][nx]>C) return ;
					
					q.add(new int[] {ny, nx});
					map[ny][nx].add(n);
				}
			}	
		}
	}
	

	
	static class BC {
		int x,y,c,p;
		
		BC (int x, int y, int c, int p) {
			this.x=x;
			this.y=y;
			this.c=c;
			this.p=p;
		}
	}
	
	static void print () {
		for (int i=1; i<=10; i++) {
			for (int j=1; j<=10; j++) {
				System.out.print(map[i][j].size()+" ");
			}
			System.out.println();
		}
	}

}
