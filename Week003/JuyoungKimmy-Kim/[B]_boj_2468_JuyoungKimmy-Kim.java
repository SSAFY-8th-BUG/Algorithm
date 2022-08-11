package bfs.dfs;

/*
 * 
 * 문제에서 내리는 비의 양의 범위가 나와있지 않으므로
 * 비가 오지 않은 경우 ~ 지역의 최대 높이 (100)까지 확인해보아야 한다
 * 혹은 구역의 높이를 입력받을 때, 높이 최대를 구하고 거기까지 확인
 * 
 * 비가 오지 않는 경우~1미만 : 모든 구역이 물에 잠기지 않으므로 1
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int y,x;
	
	Pair (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class BOJ2468 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static int N;
	static int safeArea=1;
	
	static boolean [][] checked;
	static int [][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		System.out.println(safeArea);
	}
	
	private static void solution () {
		for (int high=1; high<=100; high++) {
			
			checked =new boolean[N][N];
			
			
			 //물의 높이 high보다 작은 구역들은 침수된다
			 
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]<=high)
						checked[i][j]=true;
				}
			}
			
			int area=0;	
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					
					/*
					 * 침수되지 않은 구역 (i,j)을 체크하고
					 * 이에 연결된 영역은 다 체크를 해줌 
					 */
					if (!checked[i][j]) {
						area++;
						checked[i][j]=true;
						bfs(i,j);
					}	
				}
			}
			safeArea=Math.max(area, safeArea);
		}
	}

	private static void bfs (int i, int j) {
		
		Queue <Pair> q =new ArrayDeque<>();
		q.add(new Pair(i,j));
		
		while (!q.isEmpty()) {
			Pair cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || checked[ny][nx]) continue;
				
				checked[ny][nx]=true;
				q.add(new Pair(ny, nx));
			}
		}
	}
	

}
