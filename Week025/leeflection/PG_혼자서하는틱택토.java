package studygroup.week25;

public class PG_혼자서하는틱택토 {
    public int solution(String[] board) {
        // x가 o보다 많으면 안됨
        // o가 x보다 2개 이상 많으면 안됨

        // o가 이길때는 x가 1개 이상 많아야함
        // x가 이길땐 o와 x가 같아야함

        //위 두조건 먼저 확인해본다
        int o = 0;
        int x = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length(); j++){
                if(board[i].charAt(j) == 'O'){
                    o++;
                }else if(board[i].charAt(j)=='X'){
                    x++;
                }
            }
        }
        if(o==0 && x==0){
            return 1;
        }
        if(x>o || o > x+1){
            return 0;
        }else{
            char winner = ' ';
            int winO = 0;
            int winX = 0;
            //끝난 결과인지 확인
            for(int i=0; i<board.length; i++){
                char c = board[i].charAt(0);
                boolean ch = true;
                for(int j=0; j<board[i].length(); j++){
                    if(c != board[i].charAt(j)){
                        ch = false;
                        break;
                    }
                }
                if(ch && c!='.'){
                    winner = c;
                    if(winner == 'O'){
                        winO++;
                    }else if(winner =='X'){
                        winX++;
                    }
                }
            }
            //세로쳌
            for(int i=0; i<board.length; i++){
                char c = board[0].charAt(i);
                boolean ch = true;
                for(int j=0; j<board[i].length(); j++){
                    if(c != board[j].charAt(i)){
                        ch = false;
                        break;
                    }
                }
                if(ch && c!='.'){
                    winner = c;
                    if(winner == 'O'){
                        winO++;
                    }else if(winner=='X'){
                        winX++;
                    }
                }
            }
            //대각첵
            if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2) && board[0].charAt(0)!='.'){
                winner = board[0].charAt(0);
                if(winner == 'O'){
                    winO++;
                }else if(winner =='X'){
                    winX++;
                }
            }
            if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0) && board[0].charAt(2)!='.'){
                winner = board[0].charAt(2);
                if(winner == 'O'){
                    winO++;
                }else if(winner == 'X'){
                    winX++;
                }
            }

            if(winner == ' '){
                return 1;
            }else{
                if(winO==1 && winX==0 && x+1==o){
                    return 1;
                }
                if(winX==1 && winO==0 && x==o){
                    return 1;
                }
                if(winO==2 && winX==0 && x+1==o){
                    return 1;
                }
                return 0;
            }
        }
    }
}
