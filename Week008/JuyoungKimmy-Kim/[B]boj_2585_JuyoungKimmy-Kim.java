package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * #1. Binary Search로 최대 연료 지정 
 * 		=> 처음 최소 연료는 0, 최대 연료는 (0,0) (10_000,10_000) 거리를 지나기 위한 연료
 * 
 * #2. K번 초과한다면? -> 거리를 늘려야 함 -> 연료를 늘려야 함
 * #3. K번 이하로 받는다? -> 거리를 줄임 -> 연료를 줄여야 함
 * 
 * 
 */
public class BOJ2585 {
	
	static int N,K,ans;
	static Pos[] pos;
	static boolean[] visited;
		
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		pos=new Pos[N+2];
		visited=new boolean[N+2];
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			pos[i]=new Pos(x,y);
		}
		pos[0]=new Pos(0,0);
		pos[N+1]=new Pos(10_000,10_000);

		int low=0; 
		int high=(int)getLiter (pos[0].x, pos[0].y, pos[N+1].x, pos[N+1].y);
		
		while (low<=high) {
			int mid=(low+high)/2;
			
			/*
			 * bfs(mid)==true : 연료가 mid일 때, K번 이하로 착륙하여 도착지에 도착 -> 연료를 더 줄이고 더 많이 착륙하게 만듦
			 * 		     false: 연료가 mid일 때, mid 연료를 가지고 도착지에 도착X -> 연료를 더 늘리고 더 적게 착륙하게 만듦
			 */
			
			if (bfs(mid)) {
				high=mid-1;
				ans=mid;
			}
			else
				low=mid+1;			
		}
		System.out.println(ans);
	}
	
	private static boolean bfs (int fuel) {
		Arrays.fill(visited, false);
		Queue<Pos> q=new ArrayDeque<>();
		q.add(new Pos (0,0,0));
		visited[0]=true;
		
		while (!q.isEmpty()) {
			Pos p=q.poll();
			int y=p.y;
			int x=p.x;
			int cnt=p.cnt;
			
			if (cnt>K) continue; 
			// 현재 위치에서 마지막 도착지까지 도착할 수 있을 때 (연료:fuel, 움직인 횟수: cnt (<=K))
			if (getLiter(x,y,pos[N+1].x, pos[N+1].y) <=fuel)
				return true;
			
			
			for (int i=1; i<=N; i++) {
				if (visited[i]) continue;
				if (getLiter (x,y,pos[i].x, pos[i].y) <=fuel) {
					visited[i]=true;
					q.add(new Pos (pos[i].x, pos[i].y, cnt+1));
				}
			}
		}
		
		return false;
	}
	
	
	
	//(x1,y1) -> (x2, y2)로 갈 때 필요한 연료(L) 측정
	private static double getLiter (int x1, int y1, int x2, int y2) {
		double dist=Math.ceil(Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2)));
		return dist/10;
	}
	
	static class Pos{
		int x,y,cnt;
		
		//처음 입력받는 착륙 지점 좌표
		Pos (int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		//Queue에서 사용할 좌표
		Pos (int x, int y, int cnt) {
			this.x=x;
			this.y=y;
			this.cnt=cnt;
		}
	}

}
