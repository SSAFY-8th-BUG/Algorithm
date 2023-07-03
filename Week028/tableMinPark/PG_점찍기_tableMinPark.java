class PG_점찍기_tableMinPark {
    public long solution(int k, int d) {
        long answer = 1;

        answer += ((d / k) * 2);

        double p = Math.pow(d, 2);
        for (long i = 1; i * k <= d; i++){
            long n = i * k;
            answer += (int) Math.sqrt(p - Math.pow(n, 2)) / k;
        }

        return answer;
    }
}