// 안에서 밖으로 움직이는 위치와 방향 -> inOur
// 밖에서 안으로 움직이는 위치와 방향 -> outIn 에 저장
// k 번만큼 반복하게 하는 게 힘들었음 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static final int dy[]= {0,0,1,0,-1};
	static final int dx[]= {0,1,0,-1,0};

	static final int IOdy[]= {-1,0,1,0};
	static final int IOdx[]= {0,1,0,-1};
	
	static final int OIdy[]= {1,0,-1,0};
	static final int OIdx[]= {0,1,0,-1};
	
	static int N, M, H, K, sy,sx,sd, ans;
	static boolean[][] map;
	static int [][] tmp;
	
	static List<Runner> runner;
	static List<Order> inOut, outIn; 
	static List<int[]> area;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		map=new boolean[N+1][N+1];
		
		tmp=new int[N+1][N+1];
		
		runner=new ArrayList<>();
		inOut=new ArrayList<>();
		outIn=new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			
			runner.add(new Runner (y,x,d));
		}

		
		for (int i=0; i<H; i++) {
			st=new StringTokenizer (br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			
			map[y][x]=true;
		}
		
		simulation ();
		System.out.println(ans);
	}
	
	static void simulation () {
		
		//#1. order - inout, outin
		makeOrder ();
		
//		for (int i=1; i<inOut.size()-1; i++)
//			System.out.println(inOut.get(i).y+" "+inOut.get(i).x);
//		
//		for (int i=0; i<outIn.size(); i++)
//			System.out.println(outIn.get(i).y+" "+outIn.get(i).x);
//		
		
		//#2 k번만큼 반복 -> inOut, outIn이 시간에 따라 움직이게
		
		int Time=1;
		sy=sx=N/2+1;
		
		while (true) {
			
			for (int i=1; i<inOut.size()-1; i++) {
				run();
				
				sy=inOut.get(i).y;
				sx=inOut.get(i).x;
				sd=inOut.get(i).dir;
				
				area=new ArrayList<>();
				area.add(new int[] {sy,sx});
				area.add(new int[] {sy+IOdy[sd], sx+IOdx[sd]});
				area.add(new int[] {sy+IOdy[sd]*2, sx+IOdx[sd]*2});
			
				
				catchRunner(Time);
				
				Time++;
				if (Time==K+1) return;
			}
			
			for (int i=0; i<outIn.size(); i++) {
				run();

				sy=outIn.get(i).y;
				sx=outIn.get(i).x;
				sd=outIn.get(i).dir;
				
				area=new ArrayList<>();
				area.add(new int[] {sy,sx});
				area.add(new int[] {sy+OIdy[sd], sx+OIdx[sd]});
				area.add(new int[] {sy+OIdy[sd]*2, sx+OIdx[sd]*2});
				
				catchRunner(Time);

				
				Time++;
				if (Time==K+1) return ;
			}
			
		}
	}
	
	static void run () {
		for (int i=0; i<M; i++) {
			if (runner.get(i).alive==false) continue;
			
			int y=runner.get(i).y;
			int x=runner.get(i).x;
			int d=runner.get(i).dir;
			
			int diff=getDist(sy,sx,y,x);
			if (diff>3) continue;
			
			y+=dy[d];
			x+=dx[d];
			
			if (y==sy && x==sx) {
				y-=dy[d];
				x-=dx[d];
				continue;
			}
			
			if (y<=0 || x<=0 || y>N || x>N) {
				if (d<=2) d+=2;
				else d-=2;
				y+=(dy[d]*2);
				x+=(dx[d]*2);
				runner.get(i).dir=d;
			}
			
			if (y==sy && x==sx) continue;
			
			runner.get(i).y=y;
			runner.get(i).x=x;
			runner.get(i).dir=d;	
		}
	}
	
	static void catchRunner (int Time) {
		
		for (int i=0; i<M; i++) {
			if (runner.get(i).alive==false) continue;
			
			int y=runner.get(i).y;
			int x=runner.get(i).x;
			if (map[y][x]) continue;
			
			for (int k=0; k<3; k++) {
				int ey=area.get(k)[0];
				int ex=area.get(k)[1];
				
				if (ey==y && ex==x) {
					runner.get(i).alive=false;
					ans+=Time;
				}
			}
		}
	}
	
	static void makeOrder () {
		//in -> out
		
		int y,x;
		y=x=N/2+1;
		
		int dir=0;	//계속 변경되는 방향
		int cnt=0;	//2번마다 dist 늘려주어야 함
		int dist=1;	//지금 방향일 때 몇 번 움직여주어야 하나
		
		inOut.add(new Order (y,x,dir));
				
		outer: while (true) {
			for (int i=1; i<=dist; i++) {
				y+=IOdy[dir];
				x+=IOdx[dir];
				
				if (y==1 && x==1) {
					inOut.add(new Order (y,x,2));
					break outer;
				}
				
				if (i==dist) 
					dir=(dir+1)%4;
				
				inOut.add(new Order (y,x, dir));
			}
			cnt++;
			if (cnt==2) {
				cnt=0;
				dist++;
			}
		}
		
		// out->in
		
		y=0; x=1;
		dir=0;
		cnt=1;
		dist=N;
					
		outer: while (true) {
			for (int i=1; i<=dist; i++) {
				y+=OIdy[dir];
				x+=OIdx[dir];
				
				if (y==N/2+1 && x==N/2+1) break outer;
				
				if (i==dist) 
					dir=(dir+1)%4;	
				
				outIn.add(new Order (y,x,dir));
			}
			cnt++;
			if (cnt==2) {
				cnt=0;
				dist--;
			}
		}
	}
	

	
	static int getDist (int y1, int x1, int y2, int x2) {
		return Math.abs(y1-y2)+Math.abs(x1-x2);
	}
	
	static class Runner {
		boolean alive;
		int y,x,dir;
		
		Runner (int y, int x, int dir) {
			this.alive=true;
			this.y=y;
			this.x=x;
			this.dir=dir;
		}
	}
	
	static class Order {
		int y,x,dir;
		
		Order (int y, int x, int dir) {
			this.y=y;
			this.x=x;
			this.dir=dir;
		}
	}
	
	static void print () {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				System.out.printf("%3d", tmp[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void printRunner () {
		for (int i=0; i<M; i++) {
			if (runner.get(i).alive==false)
				System.out.print(" 죽음 ");
			System.out.println(i+" 번 도망자: (" +runner.get(i).y+", "+runner.get(i).x+" )");
		}
	}

}
