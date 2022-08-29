package week005;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 5시간+
 * 참고: boj 반례
 * 
 * 	14356KB	128ms
 * 
 * <풀이방식>
 * - bfs, 인접배열, 우선순위큐, 집합
 * 
 * - a 부터 순차적으로 섬 네이밍해줌 (bfs, a 부터 시작)
 * - 가능한 최소다리길이 인접배열에 구함 (a->0번, b->1번, ..)
 * - 인접배열의 최소다리길이들 우선순위큐에 넣음
 * - 우선순위큐에서 하나씩 빼면서 같은 집합이 아닐경우 합집합
 * - 모든 섬이 하나의 집합에 포함되면 (모든 섬 연결) 길이 리턴
 * 
 * <삽질목록>
 * - 그냥 많음..
 * 
 * */
public class Dboj_17472_다리만들기2 {
	static class Edge{
		int a, b, len;

		public Edge(int a, int b, int len) {
			super();
			this.a = a;
			this.b = b;
			this.len = len;
		}
		
	}
	static int N, M, islandN;	// 세로, 가로, 섬 개수
	static char[][] map;
	
	// 섬 이름
	static char islandChar = 'a';
	
	// 섬 네이밍에 사용되는 큐
	static Queue<Point> setq = new ArrayDeque<Point>();
	
	
	// 집함 대표 배열
	static int[] parent;

	
	// 다리길이 (간선 정렬..큐)
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((e1, e2) -> ((e1.len==e2.len) ? e1.a-e2.a : e1.len-e2.len));
	
	// 인접배열
	static int[][] adj;
	
	// 상하좌우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		// 섬 네이밍
		setIsland();
		
		//print();
		
		// 섬 이름 이용해 섬 갯수 체크
		islandN = islandChar-'a';

		// 섬 다리길이 배열
		adj = new int[islandN][islandN];
		
		// adj
		setEdge();
		setEdgeQueue();
		
		System.out.println(calcBridge());
	}
	
	
//	static void print() {
//		System.out.println("***********************");
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}

//////////////////////////////////////// 다리 길이 구하기(집합 이용) ////////////////////////////////////////////
	// 다리 길이 구하기
	static int calcBridge(){
		int cnt = 0;
		
		parent = new int[islandN];
		for(int i = 0; i < islandN; i++) parent[i] = i;

		
		boolean tail = false;
		
		for(int i = 0; i < islandN; i++) {
			int num = 0;
			for(int j = 0; j < islandN; j++) {
				if(adj[i][j] == 0) continue;
				num++;				

			}
			
			// 연결된 섬 없는 경우가 있으면 검사할 필요 없다 
			if (num==0) return -1;
			
		}
	
		int len =  _calcBridge();
		
		if(len == -1) return -1;
		return len;
	}
	// 집합 이용해 다리길이 구함
	static int _calcBridge() {
		int len = 0;
		
		// 모든 가능한 다리에 대해 검사
		while(!pq.isEmpty()) {
			// 섬 모두 연결함 (모든 섬이 하나의 집합에 포함 ) -> 완료
			if(chkOnlyUnion()) break;
			
			Edge e = pq.poll();
			
			// 연결안했을 경우(같은 집합 X)
			if(!isUnion(e.a, e.b)) {
				// 합집합 후 길이 더해줌
				union(e.a, e.b);
				len += adj[e.a][e.b];
			}
			
		}
		// 모두 연결 못함 (모든 놓을 수 있는 다리 검사했음에도 하나의 집합x)
		if(!chkOnlyUnion()) return -1;
		return len;
	}
	
///////////////////////////////////////// 집합 관련 함수 /////////////////////////////////////////////
	// 같은 집합인지 체크
	static boolean isUnion(int a, int b) {
		if(find(a) == find(b)) return true;
		return false;
	}
	// 모든 섬 연결되었는지 (모든 섬이 하나의 집합에 포함) 체크
	static boolean chkOnlyUnion() {
		for(int i = 1; i < islandN; i++) {
			if(!isUnion(i-1, i)) return false;
		}
		return true;
	}
	// 합집합
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return;
		
		parent[bRoot] = aRoot;
	}
	// 집합 대표자 찾기
	static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
///////////////////////////////////간선 관련 함수(가능한 다리)//////////////////////////////////////////
	// 구한 간선 큐에 넣음
	static void setEdgeQueue() {
		
		// adj 대각선 기준  밑부분 순회
		int col = 0;
		for(int i = 1; i < islandN; i++) {
			col++;
			for(int j = 0; j < col; j++) {
				
				// 항상 j<i 임
				if(adj[i][j] != 0)
				pq.add(new Edge(j, i, adj[i][j]));
				
			}
			
		}
	}
	// 섬과 섬 간의 최소거리 간선 구하기
	static void setEdge() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '0') continue;
				_setEdge(i, j);
			}
		}
	}
	// 상 하 좌 우 다리 놓을 수 있는지, 최소 길이 체크
	// adj 리스트에 최소길이 저장
	static void _setEdge(int y, int x) {
		char currIsland = map[y][x];
		
		int ny = y-1;
		int nx = x;
		
		int i = currIsland-'a';
		int j = 0;
		
		// 상
		int len = 0;
		while(ny>=0) {
			
			// 이동한 위치가 현재 섬인 경우
			// 다른 위치에서 검사할 수 있으므로 검사하지 않음
			if(map[ny][nx] == currIsland) break;
			
			if(map[ny][nx] != '0' && map[ny][nx] != currIsland ) {
				if(len > 1 ) {
					j = map[ny][nx]-'a';
			
				if(adj[i][j] == 0 || adj[i][j] > len)	adj[i][j] = len;
				
				}
				break;
			}
			if(map[ny][nx] != currIsland) len++;
			ny--;
		}
		
		// 하 
		len = 0;
		ny = y+1;
		while(ny<N) {
			
			if(map[ny][nx] == currIsland) break;
			
			if(map[ny][nx] != '0' && map[ny][nx] != currIsland ) {
					if(len > 1 ) {
						j = map[ny][nx]-'a';
				
					if(adj[i][j] == 0 || adj[i][j] > len)	adj[i][j] = len;
					
					}break;
			}
			if(map[ny][nx] != currIsland) len++;
			ny++;
		}

		
		// 좌
		len = 0;
		ny = y;
		nx--;
		while(nx>=0) {
			
			if(map[ny][nx] == currIsland) break;
			
			if(map[ny][nx] != '0' && map[ny][nx] != currIsland) {
				if(len > 1 ) {j = map[ny][nx]-'a';
				
				if(adj[i][j] == 0 || adj[i][j] > len)	adj[i][j] = len;
				
				}break;
			
		} if(map[ny][nx] != currIsland) len++;
			nx--;
			}
		
		
		// 우 
		len = 0;
		nx = x+1;
		while(nx<M) {
			
			if(map[ny][nx] == currIsland) break;
			
			
			if(map[ny][nx] != '0' && map[ny][nx] != currIsland ) {
				if(len > 1 ) {j = map[ny][nx]-'a';
				
				if(adj[i][j] == 0 || adj[i][j] > len)	adj[i][j] = len;
				
				}break;
			
		}if(map[ny][nx] != currIsland) len++;
			nx++;
		}
		
		
		
	}
//////////////////////////////////// 섬 네이밍( a, b, ...) //////////////////////////////////////
	static void setIsland() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]=='1')	setEachIsland(i, j);
			}
		}
		
	}
	// bfs
	// 같은 섬일 경우 map에 적음
	// a에서 시작
	static void setEachIsland(int y, int x) {
		setq.add(new Point(x, y));
		
		while(!setq.isEmpty()) {
			Point p = setq.poll();
			map[p.y][p.x] = islandChar;
			
			for(int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if(ny < 0|| nx < 0|| ny >= N || nx >= M || map[ny][nx] != '1') continue;
				
				map[ny][nx] = islandChar;
				setq.add(new Point(nx, ny));
				
			}
			
		}
		
		// 섬 갯수 늘리기 && 다음 섬 이름 만들기 (islandChar-'a' : 섬 갯수)
		islandChar++;
	}

}
