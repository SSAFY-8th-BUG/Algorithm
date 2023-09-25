package studygroup.Week041;

class PG_3xn타일링 {
    public int solution(int n) {
        long[] tiles = new long[n + 1];
        long mod = 1000000007;
        tiles[0] = 1;
        tiles[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            tiles[i] = tiles[i - 2] * 3;
            for (int j = i - 4; j >= 0 ; j -= 2) {
                tiles[i] += (tiles[j] * 2);
            }
            tiles[i] %= mod;
        }

        return (int) tiles[n];
    }
}
