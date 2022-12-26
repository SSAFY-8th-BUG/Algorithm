package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BOJ_부분문자열뽑기게임_1519 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		if (N < 10) {
			bw.write("-1");
		} else {
			// 0이면 패배, 0이 아니면 승리.
			// 승리일 경우, 가장 최소로 뺀 값을 저장.
			int[] dp = new int[N + 1];

			for (int n = 10; n <= N; n++) {
				String str = String.valueOf(n);
				Set<String> set = new HashSet<>(); // 진 부분 문자열 집합.

				for (int start = 0; start < str.length(); start++) {
					// 0으로 시작하면 안 됨.
					if (str.charAt(start) == '0') {
						continue;
					}

					String res = "";
					for (int i = start; i < str.length(); i++) {
						res += str.charAt(i);

						if (!res.equals(str)) {
							set.add(res);
						}
					}
				}

				Iterator<String> it = set.iterator();

				int min = Integer.MAX_VALUE;
				while (it.hasNext()) {
					int num = Integer.parseInt(str);
					int temp = Integer.parseInt(it.next());

					// 다음 게임의 상황이 패배하면,
					// 현재 게임은 반드시 승리함.
					if (dp[num - temp] == 0) {
						min = Math.min(min, temp);
					}
				}

				if (min != Integer.MAX_VALUE) {
					dp[n] = min;
				}
			}
			bw.write(dp[N] == 0 ? "-1\n" : (dp[N] + "\n"));
		}
		bw.flush();
		bw.close();
		br.close();
	}

}