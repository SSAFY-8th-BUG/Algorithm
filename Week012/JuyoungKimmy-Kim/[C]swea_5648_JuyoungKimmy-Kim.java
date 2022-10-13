package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Atom_ {
	boolean alive;
	int y,x,d,k;
	
	public Atom_ (int y, int x, int d, int k) {
		this.y=y;
		this.x=x;
		this.d=d;
		this.k=k;
		this.alive=true;
	}
}


public class SWEA5648_2 {

		//상 하 좌 우
	static final int dy[]= {1,-1,0,0};
	static final int dx[]= {0,0,-1,1};
	
	static final int MAX=4001;
	
	static int T, N, ans, remain;
	static List<Atom_> atom;
	static int[][] map=new int[MAX][MAX];

	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			N=Integer.parseInt(br.readLine());
			atom=new ArrayList<>();
			
			ans=0;
			remain=N;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				int K=Integer.parseInt(st.nextToken());
	
				atom.add(new Atom_ (2*(y+1000),2*(x+1000), d, K));
			}
			
			while (remain>0) {
				move();
				crush();
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
		
	static void move () {
		for (int i=0; i<N; i++) {
			if (atom.get(i).alive==false) continue;
			
			int y=atom.get(i).y;
			int x=atom.get(i).x;
			int d=atom.get(i).d;
			
			y+=dy[d]; 
			x+=dx[d];
			
			if (y<0 || x<0 || y>=MAX || x>=MAX) {
				remain--;
				atom.get(i).alive=false;
				continue;
			}
			
			atom.get(i).y=y;
			atom.get(i).x=x;
			
			map[y][x]++;
		}
	}
	
	static void crush () {
		for (int i=0; i<N; i++) {
			if (atom.get(i).alive==false) continue;
			int y=atom.get(i).y;
			int x=atom.get(i).x;
			
			if (map[y][x]==1) {
				map[y][x]=0;
				continue;
			}
			
			if (map[y][x]>1) {
				
				atom.get(i).alive=false;
				ans+=atom.get(i).k;
				
				for (int j=i+1; j<N; j++) {
					if (atom.get(j).alive==false) continue;
					if (atom.get(j).y==y && atom.get(j).x==x) {
						atom.get(j).alive=false;
						ans+=atom.get(j).k;
					}
				}
				
				remain-=map[y][x];
				map[y][x]=0;
			}
		}
	}
}
