import java.io.*;
import java.util.*;

// 시간복잡도 -> O(n)
// 공간복잡도 -> O(n)
public class boj_2161_tableMinPark {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 양방향 큐 선언
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) q.add(i);	// 모든 항목 add
		
		StringBuilder sb = new StringBuilder();
		
		while(q.size() > 1) {		// q의 항목 수가 1개 남을 때까지 반복
			sb.append(q.pollFirst()).append(" ");		// 첫 항목 poll
			q.addLast(q.pollFirst());						// 두번 째 항목을 poll 해서 마지막으로 add
		}
		sb.append(q.poll());		// 마지막으로 남은 항목 1개를 poll
		System.out.println(sb);
		br.close();
	}
}