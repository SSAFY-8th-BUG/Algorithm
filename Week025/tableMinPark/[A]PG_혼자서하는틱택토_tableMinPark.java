// 1. x의 개수가 o의 개수보다 많은 경우
// 2. o,x 둘다 완성하는 경우
// 3. o가 이기는 경우 x보다 1개 많아야함
// 4. x가 이기는 경우 o와 x의 갯수가 같아야함
class Solution {

    static char[][] b;

    public int solution(String[] board) {
        int o = 0;
        int x = 0;

        b = new char[3][3];
        for (int i = 0; i < 3; i++) {
            b[i] = board[i].toCharArray();

            for (int j = 0; j < 3; j++) {
                if (b[i][j] == 'O'){
                    o++;
                } else if(b[i][j] == 'X') {
                    x++;
                }
            }
        }

        if (x > o) {
            return 0;
        }
        if (o - x > 1){
            return 0;
        }
        if (check('O') && check('X')) {
            return 0;
        }
        if (check('O') && !(o == x + 1)) {
            return 0;
        }
        if (check('X') && !(o == x)) {
            return 0;
        }
        return 1;
    }

    boolean check(char c) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == b[i][1] &&
                    b[i][1] == b[i][2] &&
                    b[i][2] == c){
                return true;
            }
            if (b[0][i] == b[1][i] &&
                    b[1][i] == b[2][i] &&
                    b[2][i] == c){
                return true;
            }
        }
        if (b[0][0] == b[1][1] &&
                b[1][1] == b[2][2] &&
                b[2][2] == c){
            return true;
        }
        if (b[0][2] == b[1][1] &&
                b[1][1] == b[2][0] &&
                b[2][0] == c){
            return true;
        }
        return false;
    }
}