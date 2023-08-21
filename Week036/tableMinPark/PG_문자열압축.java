class PG_문자열압축 {
    public int solution(String s) {
        int answer = 1000;
        int N = s.length();

        if (N == 1) {
            return 1;
        }

        for (int i = 1; i < N / 2 + 1; i++) {
            StringBuilder sb = new StringBuilder();
            String compare = s.substring(0, i);
            int count = 1;

            for (int j = i; j < N; j += i) {
                int endIdx = j + i;
                if (endIdx > N) {
                    // 비교 문자열이 길이를 초과하면 더이상 비교할 필요가 없기 때문에
                    // count와 문자열 저장
                    if (count > 1) {
                        sb.append(count);
                    }
                    sb.append(compare);
                    compare = s.substring(j);
                    count = 1;
                    continue;
                } else if (compare.equals(s.substring(j, endIdx))) {
                    // 일치하면 카운트만 증가
                    count++;
                } else {
                    // 일치하지 않으면 이번 문자열은 더이상 비교할 필요가 없기 때문에
                    // count와 문자열 저장
                    if (count > 1) {
                        sb.append(count);
                    }
                    sb.append(compare);
                    compare = s.substring(j, endIdx);
                    count = 1;
                }
            }

            if (count > 1) {
                sb.append(count);
            }
            sb.append(compare);
            answer = Math.min(answer, sb.toString().length());
        }



        return answer;
    }
}