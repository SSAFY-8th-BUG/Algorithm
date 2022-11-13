package week016;

import java.awt.Point;
import java.io.*;
import java.util.*;

/*
 * code tree 45 예술성
 * 
 * 참고: code tree 제공 반례(5번), 정답코드(돌려만 봄ㅎ)
 * 시간: 4시간+?
 * 
 * <삽질목록>
 * - 문제 제대로 이해 못해서 잘못 돌림ㅋㅋ
 * 
 * */
public class D_ct_45_bnuri00 {
	static class Group {
		int color, cnt;

		public Group(int color, int cnt) {
			super();
			this.color = color;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Group [color=" + color + ", cnt=" + cnt + "]";
		}
		
	}
	static int n, totalScore, groupNum = 1;
	static int[][] colorArr;
	static int[][] groupArr;
	static HashMap<Integer, Group> groupMap = new HashMap<Integer, Group>();
	static HashMap<Integer, Integer> adjGroup = new HashMap<>();
	
	static Queue<Integer> rq = new ArrayDeque<>();
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		colorArr = new int[n][n];
		groupArr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				colorArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 예술점수
		setGroup();
		calcScore();
		//System.out.println(totalScore);
		
		// 초기화 -> 회전 -> 예술점수 
		for (int i = 0; i < 3; i++) {
			// 초기화
			groupNum = 1;
			groupMap.clear();
			adjGroup.clear();
			//resetGroup();
			
			int prev = totalScore;
			
			rotateOriginData();
			setGroup();
			calcScore();
			//print();
			//System.out.println(totalScore-prev);
		}
		System.out.println(totalScore);
	}
	
	private static void rotateOriginData() {
		rotateCross();
		
		int len = n/2;
		rotateSquare(0, 0, len);
		rotateSquare(0, len+1, len);
		rotateSquare(len+1, 0, len);
		rotateSquare(len+1, len+1, len);
	}
	
	// 반시계방향 돌리기
	private static void rotateCross() {
		// 십자 부분 중 왼쪽 부분 저장
		int[] tmp = new int[n/2];
		for (int i = 0; i < n/2; i++) {
			tmp[i] = colorArr[n/2][i];
		}
		
		//  ┘ 위쪽 -> 왼쪽 덮어쓰기
		for (int i = 0; i < n/2; i++) colorArr[n/2][i] = colorArr[i][n/2];
		
		//  └  오른쪽 -> 위쪽 덮어쓰기
		for (int i = 0; i < n/2; i++) colorArr[n/2-i-1][n/2] = colorArr[n/2][i+n/2+1];
		
		//  ┌ 아래쪽 -> 오른쪽 
		for (int i = 0; i < n/2; i++) colorArr[n/2][i+n/2+1] = colorArr[i+n/2+1][n/2];
		
		// ┐ 왼쪽 -> 아래쪽
		for (int i = 0; i < n/2; i++) colorArr[i+n/2+1][n/2] = tmp[n/2-i-1];
	
	}
	private static void rotateSquare(int y, int x, int len) {
		int startX = x;
		int startY = y;
		
		while(len >= 2) {
			rotateSquarePart( startY,startX, len);
			startX++; startY++; len -= 2;
		}
	}
	private static void rotateSquarePart(int y, int x, int len) {
		rq.clear();
		int[] arr = colorArr[y].clone();
		int endY = y+len-1;
		int endX = x+len-1;
		
		for (int i = 0; i < len; i++) {
			colorArr[y][endX-i] = colorArr[y+i][x];
		}
		for (int i = 0; i < len; i++) {
			colorArr[y+i][x] = colorArr[endY][x+i];
		}
		for (int i = 0; i < len; i++) {
			colorArr[endY][x+i] = colorArr[endY-i][endX];
		}
		for (int i = 0; i < len; i++) {
			colorArr[y+i][endX] = arr[x+i];
		}
		
	}
	static void print() {
		System.out.println("******************");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("\t"+colorArr[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void calcScore() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(groupArr[i][j]== 0) continue;	
				calcScorePart(i, j);
			}
		}
	}
	
	static void calcScorePart(int y, int x) {
		adjGroup.clear();
		
		// 인접 변의 수 체크
		Queue<Point> q = new ArrayDeque<>();
		int groupId = groupArr[y][x];
		groupArr[y][x] = 0;	// 방문체크
		q.add(new Point(x, y));
		
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if(ny < 0|| ny >= n || nx < 0 || nx >= n || groupArr[ny][nx]==0 ) continue;
				
				if(groupArr[ny][nx]!= groupId) {
					int tmpId = groupArr[ny][nx];
					if(!adjGroup.containsKey(tmpId)) adjGroup.put(tmpId, 1);
					else adjGroup.replace(tmpId, adjGroup.get(tmpId)+1);
					continue;
				}
				
				groupArr[ny][nx] = 0;	// 방문처리
				q.add(new Point(nx, ny));
				cnt++;
			}
		}
		int groupCnt1 = groupMap.get(groupId).cnt;
		int groupColor1 = groupMap.get(groupId).color;
		
		// 조화로움 계산
		adjGroup.forEach((key, value) -> {
			Group g2 = groupMap.get(key);
			int groupCnt2 = g2.cnt;
			int groupColor2 = g2.color;
			
			int score = (groupCnt1+groupCnt2)*groupColor1*groupColor2*value;
			totalScore += score;
		});
	}
//	private static void resetGroup() {
//		groupMap.clear();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				groupArr[i][j] = 0;
//			}
//		}	
//	}
	private static void setGroup() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(groupArr[i][j] != 0) continue;	
				setGroupPart(i, j);
			}
		}
	}
	private static void setGroupPart(int y, int x) {
		
		Queue<Point> q = new ArrayDeque<>();
		int color = colorArr[y][x];
		groupArr[y][x] = groupNum;
		q.add(new Point(x, y));
		
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if(ny < 0|| ny >= n || nx < 0 || nx >= n 
						|| groupArr[ny][nx]!=0 || colorArr[ny][nx]!= color) continue;
				
				groupArr[ny][nx] = groupNum;	// 그룹으로 세팅
				q.add(new Point(nx, ny));
				cnt++;
			}
		}
		groupMap.put(groupNum, new Group(color, cnt));
		groupNum++;
	}

}
