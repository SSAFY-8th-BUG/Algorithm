// 실패
// 누적합으로 다시 풀어봐야함
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        int size = sequence.length;

        for (int w = 1; w < size; w++) {
            for (int i = 0; i <= size - w; i++) {
                int p = 1;
                long sum1 = 0, sum2 = 0;
                for (int now = i; now < i + w; now++) {
                    sum1 += sequence[now] * p;
                    sum2 += sequence[now] * p * -1;

                    p = p == 1 ? -1 : 1;
                }
                answer = Math.max(answer, sum1);
                answer = Math.max(answer, sum2);
            }
        }
        return answer;
    }
}