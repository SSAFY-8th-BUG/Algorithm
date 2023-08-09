import java.util.*;
public class PG_가장큰정사각형찾기 {
    // 수학적 벽 느낌
    // 코드 참고
    public int solution(int [][]board)
    {
        int answer = 0;

        int[][] map = new int[board.length + 1][board[0].length + 1];

        for(int i=1;i<=board.length;i++){
            for(int j=1;j<=board[0].length;j++){
                // 원래 좌표가 0이 아니면
                if(board[i-1][j-1] != 0){
                    int len = Math.min(Math.min(map[i-1][j], map[i][j-1]), map[i-1][j-1]) + 1;
                    // 해당 좌표 주변 값 파악 후 +1
                    map[i][j] = len;
                    answer = Math.max(len, answer);
                }
            }
        }

        return answer * answer;
    }
}
