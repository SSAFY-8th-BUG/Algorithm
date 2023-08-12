class Solution {
    static int N, M;

    public int solution(int [][]board) {
        int answer = 0;

        N = board.length;
        M = board[0].length;

        if (N < 2 || M < 2) {
            return 1;
        }
        for (int y = 1; y < N; y++) {
            for (int x = 1; x < M; x++) {
                if (board[y][x] != 0) {
                    board[y][x] = Math.min(board[y - 1][x - 1], Math.min(board[y - 1][x], board[y][x - 1])) + 1;
                }

                if (answer < board[y][x]) {
                    answer = board[y][x];
                }
            }
        }
        return answer * answer;
    }
}