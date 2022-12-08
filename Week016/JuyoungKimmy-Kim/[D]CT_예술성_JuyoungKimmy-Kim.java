// 맟닿아 있는 변의 수를 구하는 방법을 찾아내는 게 힘들었음


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static int N,K,ans;
	static int[][] map;
	static List<Integer>[][] adj;
	static boolean[][] visited;

	static int[] adjCnt;
	static List<Group> group;
	static List<Info> adjInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		adj = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adj[i][j] = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();
		System.out.println(ans);

	}

	static void simulation() {
	
		for (int i=0; i<=3; i++) {
			makeGroup();
			getArtScore();
			if (i==3) break;
			rotate ();
		}

	}
	
	static void rotate () {
		crossRotate ();
		
		int L=N/2;
		squareRotate (0,0,L);
		squareRotate (L+1,0,L);
		squareRotate (0,L+1,L);
		squareRotate (L+1,L+1,L);
		
	}
	
	static void squareRotate (int y, int x, int L) {
		int [][] tmp=new int[L][L];
		
		for (int i=0; i<L; i++) 
			for (int j=0; j<L; j++)
				tmp[i][j]=map[y+L-1-j][x+i];
		
		for (int i=0; i<L; i++)
			for (int j=0; j<L; j++)
				map[y+i][x+j]=tmp[i][j];
	}
	
	static void crossRotate () {
		Queue<Integer> queue=new ArrayDeque<>();
		
		/////////////// push
		for (int y=0; y<N/2; y++)
			queue.add(map[y][N/2]);
		
		for (int x=0; x<N/2; x++)
			queue.add(map[N/2][x]);
		
		for (int y=N-1; y>N/2; y--)
			queue.add(map[y][N/2]);
		
		for (int x=N-1; x>N/2; x--)
			queue.add(map[N/2][x]);
		
		//////////////// pop
		for (int x=0; x<N/2; x++)
			map[N/2][x]=queue.poll();
		
		for (int y=N-1; y>N/2; y--)
			map[y][N/2]=queue.poll();
		
		for (int x=N-1; x>N/2; x--)
			map[N/2][x]=queue.poll();
		
		for (int y=0; y<N/2; y++)
			map[y][N/2]=queue.poll();
	}

	static void getArtScore () {
		for (Info i : adjInfo) {
			int g1=i.G1-1;
			int g2=i.G2-1;
			int length=i.length;
			
			int g1Num=group.get(g1).no;
			int g1Cnt=group.get(g1).cnt;
			
			int g2Num=group.get(g2).no;
			int g2Cnt=group.get(g2).cnt;
			
			ans+= (g1Cnt+g2Cnt) * (g1Num * g2Num * length);
		}
	}
	
	static void makeGroup() {

		group = new ArrayList<>();
		adjInfo = new ArrayList<>();
		K = 1;

		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j])
					bfs(i, j, map[i][j]);

			}
		}

	}

	static void bfs(int i, int j, int num) {
		visited[i][j] = true;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { i, j });

		adjCnt = new int[K];

		int cnt = 1;
		while (!queue.isEmpty()) {
			int y = queue.peek()[0];
			int x = queue.poll()[1];

			if (adj[y][x].size() > 0) {
				for (int s = 0; s < adj[y][x].size(); s++) {
					adjCnt[adj[y][x].get(s)]++;
				}
				adj[y][x].clear();
			}

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (!isInRange(ny, nx) || visited[ny][nx])
					continue;
				if (map[ny][nx] != num) {
					adj[ny][nx].add(K);
				} else {
					queue.add(new int[] { ny, nx });
					visited[ny][nx] = true;
					cnt++;
				}
			}
		}

		group.add(new Group(num, cnt));

		for (int k = 1; k < K; k++) {
			if (adjCnt[k] > 0) {
				adjInfo.add(new Info(k, K, adjCnt[k]));
			}
		}
		K++;

	}

	static boolean isInRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

	static class Info {
		int G1, G2;
		int length;

		Info(int G1, int G2, int length) {
			this.G1 = G1;
			this.G2 = G2;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Info [G1=" + G1 + ", G2=" + G2 + ", length=" + length + "]";
		}

	}

	static class Group {
		int no; // 그룹에 있는 번호
		int cnt;

		Group(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Group [no=" + no + ", cnt=" + cnt + "]";
		}
	}
	
	static void print () {
		System.out.println("==============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}
}
