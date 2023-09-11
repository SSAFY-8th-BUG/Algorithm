public class PG_NQueen {
    static int[] map;
    public int solution(int n) {
        int answer = 0;
        map = new int[n];

        answer = queen(0, n);

        return answer;
    }

    static int queen(int y, int n){
        if(y == n){
            return 1;
        }
        int cnt = 0;

        for(int i=0;i<n;i++){
            if(check(y, i, n)){
                map[y] = i;
                cnt += queen(y+1, n);
            }
        }
        return cnt;
    }

    static boolean check(int y, int x, int n){
        for(int i=0;i<y;i++){
            if(map[i] == x || Math.abs(map[i]-x) == y-i){
                return false;
            }
        }
        return true;
    }
}
