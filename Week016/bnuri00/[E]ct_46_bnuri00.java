package week016;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * */
public class E_ct_46_bnuri00 {
	static class Team {
		int hy, hx, ty, tx;

		public Team(int hy, int hx) {
			super();
			this.hy = hy;
			this.hx = hx;
		}

		public Team(int hy, int hx, int ty, int tx) {
			super();
			this.hy = hy;
			this.hx = hx;
			this.ty = ty;
			this.tx = tx;
		}

		@Override
		public String toString() {
			return "Team [hy=" + hy + ", hx=" + hx + ", ty=" + ty + ", tx=" + tx + "]";
		}
		
	}
	static int n, m, k, totalScore;	// 격자 크기, 팀 개수, 라운드 수, 점수 총합
	static int curSeq, curHeadY, curHeadX;	// 현재 공 맞은 사람 번호, 위치 y x
	static char[][] map;
	static boolean[][] visit;
	static ArrayList<Team> teamList = new ArrayList<>();
	
	// 우 상 좌 하
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new char[n][n];
		visit = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				char cell = st.nextToken().charAt(0);
				map[i][j] = cell;
				if(cell == '1') {
					teamList.add(new Team(i, j));
				}
			}
		}
		
		
		for (int i = 0; i < teamList.size(); i++) {
			setTeamList(i, teamList.get(i).hy, teamList.get(i).hx);
		}

		logic();
		System.out.println(totalScore);
	}
	
	// 팀 저장
	private static void setTeamList(int i, int y, int x) {
		visit[y][x] = true;
		if(map[y][x] == '3') {
			Team team = teamList.get(i);
			team.ty = y;
			team.tx = x;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx] || map[ny][nx]=='0' || map[ny][nx]=='4') continue;
			setTeamList(i, ny, nx);
		}
	}
	
	static void logic() {
		for (int i = 0; i < k; i++) {	// 라운드
			// 모든 팀에 대해 한칸 이동 수행
			for (Team team : teamList) teamGo(team);
			
			// visit 배열 초기화
			for (int j = 0; j < n; j++) Arrays.fill(visit[j], false);
			
			throwBallLoc(i);
			int score = curSeq*curSeq;
			
//			if(i > 5)
//			print();
//			
//			System.out.println("round " + i +"(" + i%(4*n) + ") : " + score);
//			System.out.println("***************************");
			totalScore += score;
			
		}
	}
	
	// round에 따라 다른 위치에 공 던짐
	static void throwBallLoc(int round) {
		round = round%(4*n); // round 0 시작, 4n 넘어가면 다시 0으로 맞추기

		if(round < n) throwBall(round, 0, 0);
		else if(round < 2*n) throwBall(n-1, round%n, 1);
		else if(round < 3*n) throwBall(n-1-round%n, n-1, 2);
		else throwBall(0, n-1-round%n, 3);
	}
	
	// y, x 위치에 dir 방향으로 공 던지기
	static void throwBall(int y, int x, int dir) {
		// 이전 공맞은사람 관련 변수 초기화
		curSeq = 0;
		curHeadY = y;
		curHeadX = x;
		
		if(map[y][x] != '0' && map[y][x] != '4') { // 던진 처음 위치에 사람 있음
			visit[y][x] = true;
			findHead(y, x, 1);
		} else {
			for (int i = 0; i < n-1; i++) {
				y = y + dy[dir];
				x = x + dx[dir];
				if(map[y][x] == '0' || map[y][x] == '4') continue;
				
				visit[y][x] = true;
				findHead(y, x, 1);
				break;
			}
		}
		// team 찾아 방향 전환
		if(curSeq != 0) { 
			for (Team team : teamList) {
				if(team.hy == curHeadY && team.hx == curHeadX) switchHeadTail(team);
			}
		}
	}
	
	// 한 라운드당 팀 한칸 이동
	static void teamGo(Team team) {
		// 꼬리 이동
		for (int d = 0; d < 4; d++) {
			int ny = team.ty + dy[d];
			int nx = team.tx + dx[d];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n ) continue;
			if(map[ny][nx] == '2') {
				// map에서 변경
				map[team.ty][team.tx] = '4';
				map[ny][nx] = '3';
				
				// teamList에 반영
				team.ty = ny;
				team.tx = nx;
				break;
			}
		}
		
		// 머리 이동
		for (int d = 0; d < 4; d++) {
			int ny = team.hy + dy[d];
			int nx = team.hx + dx[d];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n ) continue;
			if(map[ny][nx] == '4') {
				map[team.hy][team.hx] = '2';
				map[ny][nx] = '1';
				team.hy = ny;
				team.hx = nx;
				break;
			}
		}
		
	}
	

	// 팀을 받아서 머리, 꼬리 switching
	static void switchHeadTail(Team team) {
		int hy = team.hy;
		int hx = team.hx;
		
		map[hy][hx] = '3';
		map[team.ty][team.tx] = '1';
		
		team.hy = team.ty;
		team.hx = team.tx;
		team.ty = hy;
		team.tx = hx;
	}

	// 공 맞은 사람 위치, 해당 팀에서 몇번째인지 찾음
	static void findHead(int y, int x, int seq) {
		if(map[y][x] == '1') {
			curHeadY = y;
			curHeadX = x;
			curSeq = seq;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n ||visit[ny][nx]|| map[ny][nx]=='0' || map[ny][nx]=='4') continue;
			
			if(map[y][x] == '3' && map[ny][nx]=='1') continue;
			
			visit[ny][nx] = true;
			
			if(map[ny][nx]!='3')
				findHead(ny, nx, seq+1);
		}
	}
	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
