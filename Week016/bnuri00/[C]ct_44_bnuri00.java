package week016;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 잘읽기ㅜㅜ
 * 
 * 시간: 그냥 오래걸림
 * 
 * 
 * */
// codeTree 술래잡기
public class C_ct_44_bnuri00 {
	static class Runaway{
		int dir;
		boolean moved;
		public Runaway(int dir, boolean moved) {
			super();
			this.dir = dir;
			this.moved = moved;
		}
		@Override
		public String toString() {
			return "Runaway [dir=" + dir + ", moved=" + moved + "]";
		}	
		
	}
	static int n, m, h, k;	// 판 크기, 도망자 수, 나무 수, 반복 수
	static int totalScore;
	static int curTurn;
	
	static ArrayList<Runaway>[][] map;
	
	// 상 우 하 좌  (시계 방향)
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static Point itPoint;
	static int itDir;
	static boolean itIsClockwise = true;
	
	static ArrayList<Point> treeList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		itPoint = new Point(n/2, n/2);
		
		map = new ArrayList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = new ArrayList<Runaway>();
			}
		}
		
		// 도망자 입력받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int dir = -1;
			
			// 좌우로 움직이는 사람은 항상 오른쪽으로 시작, 
			// 상하로 움직이는 사람은 항상 아래쪽
			if(d == 1) dir = 1;
			else dir = 2;
			
			map[y-1][x-1].add(new Runaway(dir, false));
		}
		
		// 나무 입력받기
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			treeList.add(new Point(x-1, y-1));
		}
		
		int oneSnailTurnVal = 2*n*n;	// 1 달팽이 턴 정의 (시계 + 반시계)
		int snailTurn = k/oneSnailTurnVal+1;	// 달팽이 턴 + 1
		
		for (int i = 0; i < snailTurn; i++) {
			logic();
		}

		System.out.println(totalScore);
		
	}
	
	static void logic() {		
		itDir = 0;

		// 술래 시계방향
		int space = 1;
		while(space < n) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < space; j++) {
					oneTurnMove();
					if(j==space-1) {
						itDir++;
						if(itDir == 4) itDir = 0;
						
					}
					// 도망자 잡기
					int runawaysNum = catchRunaways();
					totalScore += curTurn*runawaysNum;
					
					if(curTurn == k) return;
				}
				
				
				// n-1 길이만큼 가는 경우는 3번 n-1만큼 이동
				// 방향도 다른 규칙으로 바뀐다
				if(space==n-1 && i==1) {
					for (int j = 0; j < space; j++) {
						oneTurnMove();
						if(j==space-1)	itDir = 2;	// 방향을 위쪽으로 전환				
						
						// 도망자 잡기
						int runawaysNum = catchRunaways();
						totalScore += curTurn*runawaysNum;
						
						if(curTurn == k) return;
					}
				}
			}
			space++;
		}
		
		// 술래 반시계방향
		space--;
		while(space> 0) {

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < space; j++) {
					oneTurnMove();
					if(j==space-1) {	// space 마지막에 방향 바꿈
						itDir--;
						if(itDir == -1) itDir = 3;
						
					}
					// 도망자 잡기
					int runawaysNum = catchRunaways();
					totalScore += curTurn*runawaysNum;
					
					if(curTurn == k) return;
				}
				
				
				// n-1 길이만큼 가는 경우는 3번임, 방향도 다른 규칙으로 바뀐다
				if(space==n-1 && i==1) {
					for (int j = 0; j < space; j++) {
						oneTurnMove();
						if(j==space-1) {	// space 마지막에 방향 바꿈
							itDir--;
							if(itDir == -1) itDir = 3;
						}
						
						// 도망자 잡기
						int runawaysNum = catchRunaways();
						totalScore += curTurn*runawaysNum;

						if(curTurn == k) return;
					}
				}
				
			}
			space--;
		}
		
	}

	// 한턴의 움직임
	static void oneTurnMove() {
		curTurn++;

		// 도망자
		runawaysMove();
		allRunawaysMoved();		
		
		// 술래
		itPoint.y += dy[itDir];
		itPoint.x += dx[itDir];
	}
	
	// 술래가 도망자 잡기 시도
	private static int catchRunaways() {
		int catchNum = 0;
		int y = itPoint.y;
		int x = itPoint.x;
		for (int i = 0; i < 3; i++) {
			// 현재 확인하는 칸에 도망자가 있는지 보기
			if(!map[y][x].isEmpty() && !checkTreeExist(y, x)) {
				catchNum += map[y][x].size();
				map[y][x].clear();
				System.out.println("("+y+", "+x+")");
			}
			
			y = y + dy[itDir];
			x = x + dx[itDir];
			
			if(y < 0 || x < 0 || y >= n || x >= n) break;

		}
		
		if(catchNum != 0) {
			System.out.println("t:"+ curTurn + " => " + (curTurn*catchNum));
		}
		return catchNum;
	}
	
	private static boolean checkTreeExist(int y, int x) {
		for (Point p : treeList) 
			if(p.y == y && p.x == x) return true;
		return false;
	}

	// 모든 도망자 움직임 완료
	// set runaway moved false
	private static void allRunawaysMoved() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j].isEmpty()) continue;
				
				for (Runaway r : map[i][j])	r.moved = false;
			}
		}
	}

	// 도망자 움직이기
	static void runawaysMove() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j].isEmpty()) continue;
				if(!canMoveDistance(i, j)) {
					for (Runaway r : map[i][j]) {
						r.moved = true;
					}
					continue;
				}
				
				for (Runaway r : map[i][j]) {
					if(r.moved) continue;
					
					int dir = r.dir;
					int ny = i+dy[dir];
					int nx = j+dx[dir];
					
					if(ny < 0 || nx < 0 || ny >= n || nx >= n) {
						dir = turnEnd(dir);
						ny = i+dy[dir];
						nx = j+dx[dir];
					}
					
					// 이동하려는 위치에 술래가 있는 경우
					if(ny==itPoint.y && nx == itPoint.x) {
						r.moved = true;
						continue;
					}
					
					map[ny][nx].add(new Runaway(dir, true));
					
				}
				removeMovedRunaways(map[i][j]);
			}
		}
	}
		

	private static boolean canMoveDistance(int y, int x) {
		int distance = Math.abs(y-itPoint.y) + Math.abs(x-itPoint.x);
		if(distance <= 3) return true;
		return false;
	}

	// 다른 곳으로 움직인 runaways 없애기
	private static void removeMovedRunaways(ArrayList<Runaway> arrayList) {
		int size = arrayList.size();
		//System.out.println("remove check list size: "+size);
		for (int i = size-1; i >= 0; i--) {
			if(! arrayList.get(i).moved) {
				arrayList.remove(i);
			}
		}
	}

	private static int turnEnd(int dir) {
		switch (dir) {
		case 0: return 2;
		case 1: return 3;
		case 2: return 0;
		case 3: return 1;
		default: return -1;
		}
	}
}
