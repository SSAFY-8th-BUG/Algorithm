package studygroup.Week031;

public class PG_쿼드압축후개수세기 {
    static int ONE;
    static int ZERO;
    static int[][] map;
    public int[] solution(int[][] arr) {
        int[] answer = {};
        map = arr;
        dfs(arr.length,0,0);
        answer = new int[2];
        answer[0] = ZERO;
        answer[1] = ONE;
        return answer;
    }
    public static void dfs(int depth,int x, int y){
        if(depth == 0){
            return;
        }
        int zero = 0;
        int one = 0;
        for(int i=x; i<x+depth; i++){
            for(int j=y; j<y+depth; j++){
                if(map[i][j] == 0){
                    zero++;
                }else{
                    one++;
                }
            }
        }
        if(zero==0){
            ONE++;
        }else if(one==0){
            ZERO++;
        }else{
            dfs(depth/2,x,y);
            dfs(depth/2,x,y+depth/2);
            dfs(depth/2,x+depth/2,y);
            dfs(depth/2,x+depth/2,y+depth/2);
        }
    }
}
