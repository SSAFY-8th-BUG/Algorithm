class PG_이모티콘할인행사_tableMinPark {
    static int[] tgt;
    static int[] sale = {10, 20, 30, 40};
    static int service = 0;
    static int value = 0;
    static int E, U;

    public int[] solution(int[][] users, int[] emoticons) {
        E = emoticons.length;
        U = users.length;
        tgt = new int[E];
        solve(0, users, emoticons);
        return new int[]{service, value};
    }

    static void solve(int tgtIdx, int[][] users, int[] emoticons) {
        if (tgtIdx == E) {
            // 체크 로직

            int s = 0;
            int v = 0;
            for (int i = 0; i < U; i++){
                int sum = 0;
                for (int j = 0; j < E; j++){
                    if (tgt[j] >= users[i][0]){
                        sum += emoticons[j] * (100 - tgt[j]) / 100;
                    }
                }
                if (sum >= users[i][1]){
                    s++;
                } else {
                    v += sum;
                }
            }

            if (s > service){
                service = s;
                value = v;
            } else if (s == service && v > value){
                value = v;
            }

            return;
        }

        for (int i = 0; i < 4; i++){
            tgt[tgtIdx] = sale[i];
            solve(tgtIdx + 1, users, emoticons);
        }
    }
}