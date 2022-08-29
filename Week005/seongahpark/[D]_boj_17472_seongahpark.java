package study;
import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, label;
	static Queue<Node> q = new ArrayDeque<>();
	static ArrayList<Node> bridge = new ArrayList<>();
	static int[][] arr;
	static boolean[][] visited;
	static int[] parent;
	
	static int [] dy = { 0, 0, 1, -1};
	static int [] dx = { 1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				// 바다는 -1, 섬은 0으로 초기화 
				// 섬 번호를 1번부터 붙여주기 위함
				arr[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		// 1. 섬 라벨링 작업
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) { 
				if(arr[i][j] == 0 && !visited[i][j]) {
					label++;
					visited[i][j] = true;
					arr[i][j] = label;
					q.add(new Node(i, j, 0));
					labeling();
				}
			}
		}
		
		// 2. 다리 놓을 수 있는 모든 경우를 bridge에 넣는다
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) { 
				if(arr[i][j] > 0) {
					q.add(new Node(i, j, 0));
					checkDist(arr[i][j]);
				}
			}
		}
		
		// 3. 크루스칼로 모든 섬을 연결하는 최소값을 찾는다
		parent = new int[label + 1];
		for(int i = 1 ; i <= label ; ++i) parent[i] = i;
		
		kruskal();
	}
	
	static class Node implements Comparable<Node> {
		int y, x, d;
		
		Node(int y, int x, int d){
			this.y = y;
			this.x = x;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}

	static void kruskal() {
		Collections.sort(bridge);
		
		int cnt = 0;
		int dis = 0;
		for(Node node : bridge) {
			if(find(node.y) != find(node.x)) {
				union(node.y, node.x);
				dis += node.d;
				cnt++;
			}
		}
		
		if(cnt != label - 1) System.out.println(-1);
		else System.out.println(dis);
	}

	static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
	}

	static void checkDist(int start) {
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				// 직진
				int dist = 0;
				boolean flag = false;
				
				while(true) {
					if(ny >= N || ny < 0 || nx >= M || nx < 0 || arr[ny][nx] == start) break;
					if(arr[ny][nx] != -1) {
						flag = true;
						break;
					}
					dist++;
					
					// 같은 방향으로 직진
					ny += dy[i];
					nx += dx[i];
				}
				
				// 다른 섬에 닿은 경우에만 거리갱신
				if(flag) {
					if(dist < 2) continue; // 다리 길이 2 미만일 경우 
					bridge.add(new Node(start, arr[ny][nx], dist));
				}
			}
		}
		q.clear();
	}

	static void labeling() {
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i = 0 ; i < 4 ; ++i) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if(ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx]) continue;
				if(arr[ny][nx] == 0) {
					visited[ny][nx] = true;
					arr[ny][nx] = label;
					q.add(new Node(ny, nx, 0));
				}
			}
		}
		q.clear();
	}
}