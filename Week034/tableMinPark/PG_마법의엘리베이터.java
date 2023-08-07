class PG_마법의엘리베이터 {
    static int answer;
    public int solution(int storey) {
        answer = Integer.MAX_VALUE;

        solve(storey, 0);

        return answer;
    }

    static void solve(int n, int count) {
        if (count >= answer) {
            return;
        }
        if (n == 0) {
            answer = count;
            return;
        }
        int mod = n % 10;
        solve(n / 10, count + mod);
        solve((n / 10) + 1, count + 10 - mod);
    }
}