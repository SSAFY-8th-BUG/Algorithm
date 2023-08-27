package studygroup.Week032;

public class PG_좌물쇠와열쇠 {
    static int[][] tmp;
    static int[][] master;
    static int[][] keys;
    static int[][] locks;
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length + (key.length*2) -2;
        int m = lock[0].length + (key[0].length*2) -2;
        locks = lock;
        master = new int[n][m];
        tmp = new int[n][m];
        keys = key;

        //확장한 맵에 좌물쇠를 박는다.
        for(int i=0; i<lock.length; i++){
            for(int j=0; j<lock[0].length; j++){
                master[i+key.length-1][j+key[0].length-1] = lock[i][j];
            }
        }
        //맵 복사용도로 하나 더만들기
        for(int i=0; i<master.length; i++){
            for(int j=0; j<master[0].length; j++){
                tmp[i][j] = master[i][j];
            }
        }

        //3번만 해도되는거 아닌가?
        //4하면 통과 3하면 실패
        for(int i=0; i<4; i++){
            if(isRight()){
                return true;
            }
            rotate();
        }
        return false;

    }
    public boolean isRight(){
        System.out.println();
        for(int i=0; i<=master.length-keys.length; i++){
            for(int j=0; j<=master[0].length-keys[0].length; j++){
                //누적연산
                for(int k=0; k<keys.length; k++){
                    for(int l=0; l<keys[0].length; l++){
                        tmp[i+k][j+l] += keys[k][l];
                    }
                }
                boolean check = true;
                //좌물쇠 범위만 조회해서 모두 1인 경우가 아니면 탈락시킴
                loop:
                for(int k=0; k<locks.length; k++){
                    for(int l=0; l<locks[0].length; l++){
                        if(tmp[k+keys.length-1][l+keys[0].length-1] != 1){
                            check =false;
                            break loop;
                        }
                    }
                }
                if(check){
                    return true;
                }
                //맵 초기화 시키기
                for(int k=0; k<tmp.length; k++){
                    tmp[k] = master[k].clone();
                }
            }
        }
        return false;
    }
    public static void rotate(){
        int[][] rotate = new int[keys.length][keys[0].length];
        for(int i=0; i<keys.length; i++){
            for(int j=0; j<keys[0].length; j++){
                rotate[j][i] = keys[i][j];
            }
        }
        for(int i=0; i<keys.length; i++){
            for(int j=0; j<keys[0].length/2; j++){
                int t = rotate[i][j];
                rotate[i][j] = rotate[i][keys[0].length-1 - j];
                rotate[i][keys[0].length-1 - j] = t;
            }
        }
        keys = rotate;
    }
}
