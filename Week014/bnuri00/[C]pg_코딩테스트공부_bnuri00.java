package week014;

public class C_pg_코딩테스트공부 {
	public int solution(int alp, int cop, int[][] problems) {

		// 알고력, 코딩력 목표치 구하기
		int algoMax = 0;
		int codeMax = 0;
		for (int i = 0; i < problems.length; i++) {
			algoMax = Math.max(problems[i][0], algoMax);
			codeMax = Math.max(problems[i][1], codeMax);
		}

		// 처음부터 모든 문제 풀 수 있다
		if (algoMax <= alp && codeMax <= cop) return 0;
		

		// 목표치보다 크면 목표치로 맞추기
		if (alp >= algoMax) alp = algoMax;
		if (cop >= codeMax) cop = codeMax;
		

		int[][] dp = new int[algoMax + 2][codeMax + 2];

		for (int i = alp; i <= algoMax; i++) {
			for (int j = cop; j <= codeMax; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		// 처음 알고력, 코딩력
		dp[alp][cop] = 0;

		// 초기 상태부터
		// 목표치까지 dp배열 만들기
		for (int i = alp; i <= algoMax; i++) {
			for (int j = cop; j <= codeMax; j++) {

				// 각각 알고리즘 공부, 코딩공부
				dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
				dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

				for (int[] p : problems) {
					
					if (i >= p[0] && j >= p[1]) {	// 풀 수 있는 문제
						
						int algoCurr = Math.min(algoMax, i+p[2]);
						int codeCurr = Math.min(codeMax, j+p[3]);
						
						dp[algoCurr][codeCurr] = Math.min(dp[algoCurr][codeCurr], dp[i][j] + p[4]);
						
					}

				}
			}
		}

		return dp[algoMax][codeMax];
	}

}
