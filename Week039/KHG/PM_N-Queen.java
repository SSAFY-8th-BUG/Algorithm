import java.util.*;

class Solution {
    static int N, answer;
    static int[][] mat;
    public int solution(int n) {
        N = n;
        mat = new int[N][N];
        answer=0;
        rec(0);
        return answer;
    }
    
    void rec(int x){
        if(x==N){
            answer++;
            /*for(int y=0; y<N; y++){
                System.out.println(Arrays.toString(mat[y]));
            }System.out.println("====");*/
            return;
        }
        if(x==0){
            for(int y=0; y<N; y++){
                mat[y][x]=1;
                rec(x+1);
                mat[y][x]=0;
            }
        }else{
            for(int y=0; y<N; y++){
                boolean attack=false;
                for(int i=1; i<=x; i++){
                    if(x-i>=0 && mat[y][x-i]==1) { //왼쪽 확인
                        attack = true;
                        break;
                    }
                    if(x-i>=0 && y-i>=0 && mat[y-i][x-i]==1){ //왼쪽위 확인
                        attack = true;
                        break;
                    }
                    if(x-i>=0 && y+i<N && mat[y+i][x-i]==1){ //왼쪽아래 확인
                        attack = true;
                        break;
                    }
                    
                }
                if(!attack){
                    mat[y][x]=1;
                    rec(x+1);
                    mat[y][x]=0;    
                }
                
            }
        }
    }
}