package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 시간복잡도: O(n)
 * 공간복잡도: O(n) */
public class boj2161 {
	static Queue<Integer> q = new LinkedList<>();
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) q.add(i);
		
		while(true) {
			System.out.print(q.poll() + " ");	// 카드 버리기
			
			if(!q.isEmpty()) {	// queue가 비어있지 않을 경우 맨앞 카드 빼서 맨뒤로 옮기기
				int n = q.poll();
				q.add(n);
			} else	break;
		}

	}

}
