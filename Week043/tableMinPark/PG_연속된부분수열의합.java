class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int N = sequence.length;

        int len = Integer.MAX_VALUE;
        int sum = 0, head = 0, tail = 0;
        while(head <= tail && tail < N) {
            if (head == tail) {
                sum = sequence[head];
            }

            if (sum == k) {
                if (len > tail - head + 1) {
                    len = tail - head + 1;
                    answer[0] = head;
                    answer[1] = tail;
                }

                sum -= sequence[head];

                if (tail + 1 < N) {
                    sum += sequence[tail + 1];
                }
                if (head == tail) {
                    break;
                }
                head++;
                tail++;

            } else if (sum > k) {
                sum -= sequence[head];
                head++;
            } else if (sum < k) {
                if (tail + 1 < N) {
                    sum += sequence[tail + 1];
                }
                tail++;
            }
        }

        return answer;
    }
}