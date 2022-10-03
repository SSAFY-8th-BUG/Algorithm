package week010;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 많이 헷갈림
 * 
 * 풀이시간: 6시간+
 * 참고: X
 * 
 * <풀이>
 * - 중복순열, 큐, 셋 이용
 * 
 * - 입력 배열로 받고
 * - 데이터 currMap에 복사해서 중복순열 완탐
 * 
 * - currMap
 * - 왼쪽 아래를 (0,0)으로, 맨 밑의 행이 맨앞, 맨 위의 행이 맨 뒤 
 * - 각 열을 list에 담아 관리
 * - currMap[0] -> 0열..
 * - ex) 
 * 		input:
 * 			1 2
 * 			3 4	
 * 			->	currMap[0] : [3, 1] 
 * 				currMap[1] : [4, 2]
 * 
 * - 공 떨어뜨리는 순서에도 영향을 받으므로 중복순열 사용
 * 
 * - findBreakBricks 함수로 벽돌 깬다
 * 
 * */
public class Cswea_5656_bnuri00 {

	static int N, W = 5, H;
	static int brickNumOrigin;
	static int minRemain = Integer.MAX_VALUE;
	static int[][] input;
	static LinkedList<Integer>[] currMap;
	
	static int[] tgt;
	
	// 터질 벽돌 위치 저장 
	static TreeSet<Point> removeSet = new TreeSet<Point>((p1, p2 ) -> p2.y-p1.y == 0 ? p2.x-p1.x : p2.y-p1.y);
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/swea5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 남은 벽돌 개수 init
			minRemain = Integer.MAX_VALUE;
			
			input = new int[H][W];
			
			// 입력 받기
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					
					if(tmp != 0) input[i][j] = tmp;
				}
			}
			
			currMap = new LinkedList[W];
			for (int i = 0; i < W; i++) {
				currMap[i] = new LinkedList<Integer>();
			}
			setCurrMap();
			brickNumOrigin = calcMap();
			
			// 중복 조합, N개 column 선택 완료되면 벽돌 깨기
			tgt = new int[N];
			perm(0);
			
			System.out.println("#"+t+" " + minRemain);
		}
		
	}
	// 중복순열
	static void perm(int index) {
		// 가지치기
		if(minRemain == 0) return;
		
		if(index == N) {
			// complete code			
			for (int i = 0; i < N; i++) {
				findBreakBricks(tgt[i]);	// tgt[i] 열에 공을 떨어뜨리고 벽돌 깨기
				breakBricks();	// 깨진 벽돌 currMap에 반영
				
				// 가지치기
				if(calcMap() == 0) {
					minRemain = 0;
					return;
				}
				
			}
			// 남은 벽돌 개수 세기
			int remain = calcMap();
			minRemain = Math.min(minRemain, remain);
			
			// 벽돌 초기 상태로
			clearMap();
			setCurrMap();
			return;
		}
		
		for (int i = 0; i < W; i++) {
			tgt[index] = i;
			perm(index + 1);
			
		}
		
	}

	// 남은 벽돌 계산
	private static int calcMap() {
		int remain = 0;
		for (int i = 0; i < currMap.length; i++) {
			remain += currMap[i].size();
		}
		return remain;
	}
	// 벽돌 다 없애기
	private static void clearMap() {
		for (int i = 0; i < currMap.length; i++) {
			currMap[i].clear();
		}
	}
	// 터진 벽돌 (removeSet 에 있는 벽돌) currMap에서 없애기, removeSet clear
	private static void breakBricks() {
		removeSet.forEach(el -> {
			currMap[el.x].remove(el.y);
		});
		removeSet.clear();
		
	}
	
	// 터뜨릴 벽돌 찾기
	static void findBreakBricks(int col) {
		
		// 해당 열이 빈 경우 (벽돌 없음) 종료
		if(currMap[col].isEmpty()) return;
		
		// 공 떨어뜨린 column의 맨 뒤(맨 위)에 있는 벽돌을 큐에 넣음
		Queue<Point> q = new ArrayDeque<Point>();
		q.add(new Point(col, currMap[col].size()-1));
		
		while(!q.isEmpty()) {
			
			Point p = q.poll();
			int brick = currMap[p.x].get(p.y);
			
			removeSet.add(p);
			
			// if brick == 1
			// need to remove one brick -> 큐에 넣지 않음
			if(brick == 1) continue;
			
			// 세로
			int top = Math.min(p.y+brick-1, currMap[p.x].size()-1);
			int bottom = Math.max(p.y-brick+1, 0);
			for (int i = bottom; i <= top; i++) {
				
				// p 위치임, 다시 큐에 들어가면 무한루프..
				if(i==p.y) continue;
				
				int brickTmp = currMap[p.x].get(i);
				
				// 벽돌 위치
				Point tmpP = new Point(p.x, i);
				
				// 제거해야 될 set에 들어간 벽돌, 다시 큐에 들어가면 무한루프됨..
				if(removeSet.contains(tmpP)) continue;
				
				// else if brick > 1 && 제거 안된 벽돌
				// explode and break other brick
				removeSet.add(tmpP);
				
				// 벽돌 크기 1이면 자기 혼자만 사라지므로 큐에 안넣어도 됨
				if(brickTmp == 1) continue;
				
				q.add(tmpP);
			}
			
			// 가로
			int left = Math.max(p.x-brick+1, 0);
			int right = Math.min(p.x+brick-1, W-1);
			for (int i = left; i <= right; i++) {
				if(currMap[i].size()-1 < p.y ) continue;
				
				// p 위치임
				if(i==p.x) continue;
				
				int brickTmp = currMap[i].get(p.y);
				
				Point tmpP = new Point(i, p.y);
				
				// 제거해야 될 set에 들어간 벽돌, 다시 큐에 들어가면 무한루프됨..
				if(removeSet.contains(tmpP)) continue;
				
				removeSet.add(tmpP);
				
				if(brickTmp == 1) continue;
				
				q.add(tmpP);
			}
			
			
		}
		
		
		
	}

	// input 배열 이용해 벽돌 원래 상태로 만들기
	static void setCurrMap() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(input[i][j] != 0)
					currMap[j].addFirst(input[i][j]);
			}
			
		}
	}

}
