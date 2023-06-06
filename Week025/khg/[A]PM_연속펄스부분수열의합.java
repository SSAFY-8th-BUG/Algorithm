import java.util.*;

class Solution {
    static char[][] board;
    static char turn;
    public int solution(String[] board2) {
        int answer = -1;
        board = new char[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                board[i][j] = board2[i].charAt(j);
        if(checkCnt()==0) return 0;
        if(checkOver()==0) return 0;
        return 1;
        
    }
    
    static int checkCnt(){
        int cnto=0, cntx=0;
        for(int y=0; y<3; y++){
            for(int x=0; x<3; x++){
                if(board[y][x]=='O') cnto++;
                else if(board[y][x]=='X') cntx++;
            }
        }
        //갯수 체크
        if(cnto== cntx){turn='O'; return 1;}
        if( cnto == cntx+1){turn='X'; return 1;}
        return 0;
    }
    
    static int checkOver(){
        for(int i=0; i<3; i++){ //상하좌우 체크
            if(board[i][0]!='.' && board[i][0]==turn ){
                if(board[i][0] == board[i][1] && board[i][0] == board[i][2]) return 0;
            }
            if(board[0][i]!='.' && board[0][i]==turn){
                if(board[0][i] == board[1][i] && board[0][i] == board[2][i]) return 0;
            }
        }
        if(board[1][1] != '.' && board[1][1]==turn){ //대각선 체크
            if(board[1][1] == board[0][0] && board[1][1] == board[2][2]) return 0;
            if(board[1][1] == board[2][0] && board[1][1] == board[0][2]) return 0;
        }
        return 1;
    }
}