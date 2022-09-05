import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 풀이시간: 20분?
 * 참고: X
 * 
 * 42232KB	292ms
 * 
 * <풀이방식>
 * - bfs, delta
 * 
 * */
public class Aboj_7562_bnuri00 {

	static int N, result;
	static boolean[][] visit;

	static int sy, sx;
	static int ey, ex;

	static int dy[] = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int dx[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static Queue<Point> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());

			q.clear();
			visit = new boolean[N][N];
			bfs();
			
			System.out.println(result);
		}

	}

	static void bfs() {
		q.add(new Point(sx, sy));

		int len = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point p = q.poll();

				if (p.x == ex && p.y == ey) {
					result = len;
					return;
				}
				
				for (int d = 0; d < 8; d++) {
					int nx = p.x+dx[d];
					int ny = p.y+dy[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) continue;
					
					
					visit[ny][nx] = true;
					q.add(new Point(nx, ny));
				}
				
			}

			len++;
		}

	}

}
