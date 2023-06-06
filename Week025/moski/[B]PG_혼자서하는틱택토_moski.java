import java.util.*;
import java.io.*;

class Solution {

    static char[][] map;
    // 우, 우아, 아, 왼아
    static int[] dx = {1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1};

    public int solution(String[] board) {

        // 불가능한 조건
        // X의 개수가 O보다 많은 경우(O가 선공이기 때문에)
        // O X의 개수 차이가 2이상인 경우
        // O와 X가 같은데 O의 한줄이 완성이 되어 있는 경우
        // O와 X의 개수 차이가 1개 일때 X가 게임을 끝냈던 경우 인가?

        map = new char[3][3];
        int oCnt = 0;
        int xCnt = 0;
        int answer = 1;

        for(int i=0; i<3; i++){
            map[i] = board[i].toCharArray();
            for(int j=0; j<3; j++){
                if(map[i][j] == 'O') oCnt++;
                else if(map[i][j] == 'X') xCnt++;
            }
        }

        // X의 개수가 O보다 많은 경우(O가 선공이기 때문에)
        if(xCnt > oCnt) {
            answer = 0;
            return answer;
        }

        // O X의 개수 차이가 2이상인 경우
        if(Math.abs(xCnt-oCnt) > 1){
            answer = 0;
            return answer;
        }

        // O와 X가 같은데 O의 한줄이 완성이 되어 있는 경우
        if(xCnt == oCnt){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(map[i][j] == 'O'){
                        if(i == 0 || j == 0){
                            if(check(i, j, 'O')){
                                answer = 0;
                                return answer;
                            }
                        }
                        // 오른쪽 대각선아래 아래 순으로 체크
                    }
                }
            }
        }
        // O와 X의 개수 차이가 1개 일때 X가 게임을 끝냈던 경우
        if(oCnt == xCnt + 1){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(map[i][j] == 'X'){
                        if(i == 0 || j == 0){
                            if(check(i, j, 'X')){
                                answer = 0;
                                return answer;
                            }
                        }
                        // 오른쪽 대각선아래 아래 순으로 체크
                    }
                }
            }
        }


        answer = 1;
        return answer;
    }

    static boolean check(int y, int x, char c){

        if(map[y][x] != c) return false;


        for(int d=0; d<4; d++){

            int ny = y;
            int nx = x;

            int cnt = 1;

            while(cnt < 3){
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(ny < 0 || nx < 0 || ny >= 3 || nx >= 3) break;
                if(map[ny][nx] != c) break;
                if(map[ny][nx] == c) cnt++;
            }

            if(cnt == 3) return true;

        }
        return false;
    }
}