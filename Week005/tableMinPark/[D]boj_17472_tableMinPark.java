import java.io.*;
import java.util.*;

// 다리만들기
public class boj_17472_tableMinPark {
	
	static int N, M, answer;
	static int[][] map;
	static boolean[][] v;
	static int[] parent;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0};
	static PriorityQueue<Vertex> costs = new PriorityQueue<>((v1, v2) -> v1.d - v2.d);
	
	static class Vertex{
		int v1;
		int v2;
		int d;
		public Vertex(int v1, int v2, int d) {
			this.v1 = v1;
			this.v2 = v2;
			this.d = d;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + d;
			result = prime * result + v1;
			result = prime * result + v2;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (d != other.d)
				return false;
			if (v1 != other.v1)
				return false;
			if (v2 != other.v2)
				return false;
			return true;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
				
		map = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) map[y][x] = Integer.parseInt(st.nextToken());
		}
				
		// 땅따먹기
		int land = 1;
		v = new boolean[N][M];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0 || v[y][x]) continue;
				dfs(y, x, land++);
			}
		}		
		land--;
				
		// 가능한 다리 저장
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0) bridge(y, x);
			}
		}
		
		// Union-Find
		parent = new int[land + 1];
		for (int i = 1; i <= land; i++) parent[i] = i;
		
		answer = 0;
		int count = 0;
		while(!costs.isEmpty()) {
			if (count == land - 1) break;			
			Vertex vertex = costs.poll();		
			if (!union(vertex.v1, vertex.v2)) continue;
			count++;
			answer += vertex.d;
		}
		
		for (int i = 2; i <= land; i++) {
			if (findSet(i - 1) != findSet(i)) {
				answer = 0;
				break;
			}
		}
		System.out.println(answer == 0 ? -1 : answer);		
		br.close();
	}
	
	static void check() {
		
	}
	
	
	static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if (a == b) return false;
		if (a > b) parent[b] = a;
		else parent[a] = b;
		return true;
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		return  parent[x] = findSet(parent[x]);
	}
	
	static void bridge(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || map[nextY][nextX] == 0) continue;
			
			int dis = 0;			
			int from = map[nextY][nextX];
			
			while(true) {
				nextY += dy[i] * -1;
				nextX += dx[i] * -1;
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) break;
				if (map[nextY][nextX] != 0) {
					if (dis >= 2) costs.add(new Vertex(from, map[nextY][nextX], dis));
					break;
					// && !costs.contains(new Vertex(map[nextY][nextX], from, dis))
				}
				dis++;
			}
		}
	}
	
	static void dfs(int y, int x, int land) {
		map[y][x] = land;
		v[y][x] = true;
		
		for (int i = 0; i < 4; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX] || map[nextY][nextX] == 0) continue;
			
			dfs(nextY, nextX, land);
		}
	}
}
