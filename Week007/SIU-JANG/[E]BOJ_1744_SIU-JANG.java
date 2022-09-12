package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ_수묶기_1744 {
	
	static int N, ans;
	static List<Integer> pos = new ArrayList<>();
	static List<Integer> neg = new ArrayList<>();
	static boolean hasZero;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp > 0) pos.add(temp);
			else if (temp < 0) neg.add(temp);
			else hasZero = true;
		}
		
		// List를 정렬
		// 5, 4, 3, 2, 1
		Collections.sort(pos, Collections.reverseOrder());
		// -1, -2, -3
		Collections.sort(neg, Collections.reverseOrder());
		
		if (pos.size() == 0) {
			// do nothing
		} else if (pos.size() == 1) {
			ans += pos.get(0);
		} else if (pos.size() == 2) {
			if (pos.get(0) == 1 || pos.get(1) == 1) ans += (pos.get(0) + pos.get(1));
			else ans += (pos.get(0) * pos.get(1));
		} else {
			// pos의 개수가 짝수 일 때
			if (pos.size() % 2 == 0) {
				for (int i = 0; i < pos.size(); i += 2) {
					if (pos.get(i) == 1 || pos.get(i + 1) == 1) ans += (pos.get(i) + pos.get(i + 1));
					else ans += (pos.get(i) * pos.get(i + 1));
				}
			}
			// pos의 개수가 홀수 일 때
			else {
				for (int i = 0; i < pos.size() - 1; i += 2) {
					if (pos.get(i) == 1 || pos.get(i + 1) == 1) ans += (pos.get(i) + pos.get(i + 1));
					else ans += (pos.get(i) * pos.get(i + 1));
				}
				
				ans += pos.get(pos.size() - 1);
			}
		}
		
		// 0이 있을 때
		if (hasZero) {
			// neg의 개수가 1개 일 때
			if (neg.size() == 1) {
				ans += (0 * neg.get(0));
			}
			// neg의 개수가 1개가 X
			else {
				//neg의 개수가 짝수 일 때
				if (neg.size() % 2 == 0) {
					for (int i = 0; i < neg.size(); i += 2) {
						ans += (neg.get(i) * neg.get(i + 1));
					}
				}
				// neg의 개수가 홀 수 일 때
				else if (neg.size() > 1) {
					for (int i = 0; i < neg.size() - 1; i += 2) {
						ans += (neg.get(i) * neg.get(i + 1));
					}
					
					ans += (0 * neg.get(neg.size() - 1));
				}
			}
		}
		// 0이 없을 때
		else {
			// -3, -2, -1
			Collections.sort(neg);
			// neg의 개수가 1개 일 때
			if (neg.size() == 1) {
				ans += neg.get(0);
			}
			// neg의 개수가 1개가 X
			else if (neg.size() > 1) {
				//neg의 개수가 짝수 일 때
				if (neg.size() % 2 == 0) {
					for (int i = 0; i < neg.size(); i += 2) {
						ans += (neg.get(i) * neg.get(i + 1));
					}
				}
				// neg의 개수가 홀 수 일 때
				else {
					for (int i = 0; i < neg.size() - 1; i += 2) {
						ans += (neg.get(i) * neg.get(i + 1));
					}
					
					ans += neg.get(neg.size() - 1);
				}
			}
		}
		
		System.out.println(ans);
	}
}
