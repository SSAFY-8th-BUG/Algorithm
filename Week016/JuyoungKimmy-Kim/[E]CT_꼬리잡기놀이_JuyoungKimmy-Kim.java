import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static final int[] dy = { 0, 0, 1, -1 };
	static final int[] dx = { 1, -1, 0, 0 };

	static int N, M, K, ans; // 크기, 팀의 수, 라운드 수
	static int[][] map, teamMap;
	static boolean[][] visited;
	static Team[] team;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		teamMap = new int[N][N];
		visited = new boolean[N][N];

		team = new Team[M];
		for (int i = 0; i < M; i++)
			team[i] = new Team();

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
		makeTeam();

		for (int k = 0; k < K; k++) {

			//System.out.println(k+"---------------------");
			//System.out.println("move");
			moveForward();

			//print();
			int turn = k;
			while (turn >= 4*N)
				turn -= 4*N;
			
			//System.out.println("throw");
			throwBall(turn / N, turn % N, k + 1);
			//print();
		}
	}

	static void turn(int no, int y, int x, int k) {
		no -= 1;

		int tail = team[no].tail;
		int size = team[no].size;

		for (int i = 0; i < size; i++) {
			int ty = team[no].coord.get(i)[0];
			int tx = team[no].coord.get(i)[1];

			if (y == ty && x == tx) {
				ans += (i + 1) * (i + 1);
				break;
			}
		}

		List<int[]> tmp = new ArrayList<>();
		for (int i = tail; i >= 0; i--) {
			int ty = team[no].coord.get(i)[0];
			int tx = team[no].coord.get(i)[1];

			tmp.add(new int[] { ty, tx });

			if (i == tail)
				map[ty][tx] = 1;
			if (i == 0)
				map[ty][tx] = 3;
		}

		for (int i = size - 1; i > tail; i--) {
			int ty = team[no].coord.get(i)[0];
			int tx = team[no].coord.get(i)[1];

			tmp.add(new int[] { ty, tx });
		}

		team[no].coord = tmp;
	}

	static void throwBall(int a, int b, int k) {

		int y = 0;
		int x = 0;

		if (a == 0) {
			y = b;
			x = 0;

			if (map[y][x] != 0 && map[y][x] != 4) {
				turn(teamMap[y][x], y, x, k);
				return;
			}

			while (x < N && (map[y][x] == 0 || map[y][x] == 4))
				x++;

			if (x == N)
				return;
			turn(teamMap[y][x], y, x, k);

		} else if (a == 1) {
			y = N - 1;
			x = b;
			if (map[y][x] != 0 && map[y][x] != 4) {
				turn(teamMap[y][x], y, x, k);
				return;
			}

			while (y >= 0 && (map[y][x] == 0 || map[y][x] == 4))
				y--;

			if (y < 0)
				return;
			turn(teamMap[y][x], y, x, k);

		} else if (a == 2) {
			y = N - 1 - b;
			x = N - 1;
			if (map[y][x] != 0 && map[y][x] != 4) {
				turn(teamMap[y][x], y, x, k);
				return;
			}
			while (x >= 0 && (map[y][x] == 0 || map[y][x] == 4))
				x--;

			if (x < 0)
				return;
			turn(teamMap[y][x], y, x, k);

		} else if (a == 3) {
			y = 0;
			x = N - 1 - b;
			if (map[y][x] != 0 && map[y][x] != 4) {
				turn(teamMap[y][x], y, x, k);
				return;
			}
			while (y < N && (map[y][x] == 0 || map[y][x] == 4))
				y++;

			if (y == N)
				return;
			turn(teamMap[y][x], y, x, k);
		}
	}

	static void moveForward() {
		for (int i = 0; i < M; i++) {
			// 제일 끝에 있는 게 앞으로 오게 됨
			List<int[]> tmp = new ArrayList<>();

			int size = team[i].size;
			int tail = team[i].tail;
			
			int y = team[i].coord.get(size - 1)[0];
			int x = team[i].coord.get(size - 1)[1];

			tmp.add(new int[] { y, x });
			map[y][x] = 1;

			for (int s = 0; s < size - 1; s++) {
				y = team[i].coord.get(s)[0];
				x = team[i].coord.get(s)[1];
				tmp.add(new int[] { y, x });

				if (s == tail - 1)
					map[y][x] = 3;
				else if (s < tail)
					map[y][x] = 2;
				else
					map[y][x] = 4;
			}

			team[i].coord = tmp;
		}
	}

	static void makeTeam() {
		int k = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					dfs(i, j, k);

					team[k].size = team[k].coord.size();
					k++;
				}
			}
		}
	}

	static void dfs(int i, int j, int k) {
		team[k].coord.add(new int[] { i, j });
		visited[i][j] = true;
		teamMap[i][j]=k+1;
		
		if (map[i][j] == 3) {
			team[k].tail = team[k].coord.size() - 1;
		}

		for (int d = 0; d < 4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];

			if (!isInRange(ny, nx) || visited[ny][nx] || map[ny][nx] == 0)
				continue;
			if (map[i][j] == 1 && map[ny][nx] != 2)
				continue;

			dfs(ny, nx, k);
		}
	}

	static boolean isInRange(int y, int x) {
		if (y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

	static void print() {
		System.out.println("========================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Team {
		List<int[]> coord;
		int size, tail;

		Team() {
			coord = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Team [coord=" + coord + ", size=" + size + ", tail=" + tail + "]";
		}

	}

}
