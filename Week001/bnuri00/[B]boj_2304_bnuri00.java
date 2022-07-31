package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 시간복잡도: O(n^2)
 * 공간복잡도: O(n) */
public class boj2304 {
	static int topH = 0;
	static int L1 = -1; // 가장 높은 기둥 중 맨 앞
	static int L2 = -1; // 가장 높은 기둥 중 맨 뒤	(가장 높은 기둥 하나일 경우 L1과 동일)
	static Col[] inputCols;

	public static int logic() {
		int N = inputCols.length;
		Deque<Col> dqLeft = new ArrayDeque<>();
		Deque<Col> dqRight = new ArrayDeque<>();

		int result = 0;
		
		
		for(Col col : inputCols) {
			if(col.L < L1) {	// L1보다 앞에 위치한 기둥
				if (dqLeft.isEmpty() || col.H > dqLeft.getLast().H)
					dqLeft.add(col);
			}
			else if(col.L > L2) {	//L2보다 뒤에 위치한 기둥
				if (dqRight.isEmpty() || col.H <= dqRight.getLast().H) {
					if(!dqRight.isEmpty()  && col.H == dqRight.getLast().H) dqRight.removeLast();
					dqRight.add(col);
				} else {	
					while (!dqRight.isEmpty() &&col.H > dqRight.getLast().H)	// 현재 기둥이 deque의 마지막 기둥보다 클 경우 제거
						dqRight.removeLast();
					dqRight.addLast(col);
				}
			}
		}
		inputCols = null;

		if (!dqLeft.isEmpty()) { // 가장 높은 기둥(L1) 기준 왼쪽
			while (true) { 
				Col c = dqLeft.removeFirst();
				if (dqLeft.isEmpty()) {
					result += c.H * (L1 - c.L);
					break;
				}
				result += c.H * (dqLeft.getFirst().L - c.L);
			}
		}
		if (!dqRight.isEmpty()) { // 가장 높은 기둥(L2) 기준 오른쪽
			while (true) { 
				Col c = dqRight.removeLast();
				if (dqRight.isEmpty()) {
					result += c.H * (c.L - L2);
					break;
				}
				result += c.H * (c.L - dqRight.getLast().L);
			}
		}

		return result;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		try {
			System.setIn(new FileInputStream("input.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		inputCols = new Col[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			if (topH < H) { // 가장 높은 기둥의 높이, 위치 체크
				topH = H;
				L1 = L;
				L2 = L;
			} else if (topH == H) { // 가장 높은 기둥 2개 이상일 경우
				if (L > L2)
					L2 = L;
				else if (L < L1)
					L1 = L;
			}
			inputCols[i] = new Col(L, H);

		}
		br.close();
		Arrays.sort(inputCols,(c1,c2)->{return c1.L-c2.L;});
		System.out.print(logic() + topH * (L2-L1+1));

	}

}

class Col {
	int L = -1; // 기둥 왼쪽 위치
	int H = 0; // 기둥 높이

	public Col(int L, int H) {
		this.L = L;
		this.H = H;
	}
}
