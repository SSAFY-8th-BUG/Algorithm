package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {

	static int N, ans;
	static int[][] score;
	static int[] order = new int[9]; // 타순 저장
	static boolean[] selected = new boolean[9]; // 이미 뽑았는지?

	static boolean[] ground;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		score = new int[N][9];

		// 1번 선수 -> 4번 타자로 미리 지정
		selected[3] = true;
		order[3] = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++)
				score[i][j] = Integer.parseInt(st.nextToken());
		}

		perm(1);
		System.out.println(ans);
	}

	private static void perm(int idx) {
		if (idx == 9) {
			play();
			return;
		}

		for (int i = 0; i < 9; i++) {

			if (selected[i])
				continue;

			selected[i] = true;
			order[i] = idx;
			perm(idx + 1);
			selected[i] = false;
		}
	}

	private static boolean move() {
		boolean flag = false;

		for (int i = 3; i >= 0; i--) {
			if (ground[i]) {
				if (i == 3) {
					ground[3] = false;
					flag = true;
				} else {
					ground[i + 1] = true;
					ground[i] = false;
				}
			}
		}
		return flag;
	}

	private static void play() {

		int count = 0; // 현재 order로 얻을 수 있는 점수
		int idx = 0; // order의 index
		int playNo = order[idx]; // 현재 선수 번호
		int out = 0; // out의 개수

		for (int i = 0; i < N; i++) {

			// 매번 이닝이 시작될 때마다 ground와 out을 초기화 해줌
			out = 0;
			ground = new boolean[4];

			while (true) {
				int hit = score[i][playNo];

				switch (hit) {
				case 0:
					out++;
					break;

				case 1:
					for (int k = 3; k >= 1; k--) {
						if (ground[k]) {
							if (k == 3) {
								count++;
								ground[k] = false;
								continue;
							}
							ground[k] = false;
							ground[k + 1] = true;
						}
					}
					ground[1] = true;
					break;

				case 2:
					for (int k = 3; k >= 1; k--) {
						if (ground[k]) {
							if (k == 3 || k == 2) {
								count++;
								ground[k] = false;
								continue;
							}

							ground[k] = false;
							ground[k + 2] = true;
						}
					}
					ground[2] = true;
					break;

				case 3:
					for (int k = 1; k <= 3; k++) {
						if (ground[k]) {
							count++;
							ground[k] = false;
						}
					}
					ground[3] = true;
					break;

				case 4:
					for (int k = 1; k <= 3; k++) {
						if (ground[k]) {
							count++;
							ground[k] = false;
						}
					}
					count++;
					break;
				}

				idx = (idx + 1) % 9;
				playNo = order[idx];
				if (out == 3)
					break;
			}
		}

		if (count > ans)
			ans = count;
	}
}
