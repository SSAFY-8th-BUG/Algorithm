import java.util.*;

class Solution {
    static int H,W,N;
    static Set<Integer> answers;
    static int visited;
    static String[][] relations;
    public int solution(String[][] relation) {
        int answer = 0;
        relations = relation;
        H = relation.length;
        W = relation[0].length;
        answers = new HashSet<>();
        
        //길이 1부터 체크
        for(int len=1; len<=W; len++){
            visited=0;
            N = len;
            comb(0,0);
        }
        
        answer = answers.size();
        return answer;
    }
    
    //콤비네이션
    void comb(int idx, int n){
        if(n==N){
            if(isUnique()){ //유일성 체크
                answers.add(visited);
            }
            return;
        }
        for(int i=idx; i<W; i++){
            visited |= 1<<i;
            if(isMin(n)){ //최소성체크
                comb(i+1, n+1);
            } 
            visited ^= 1<<i;
        }
    }
    
    boolean isMin(int n){
        Iterator itr = answers.iterator();
        while(itr.hasNext()){
            int num = (int)itr.next();
            if((num | visited) == visited){
                return false;
            }
        }
        return true;
    }
    
    boolean isUnique(){
        Set<String>set = new HashSet<>();
        int[] cols = new int[N];
        int idx=0;
        for(int i=0; i<W; i++){ //bit를 배열로
            int bit= 1<<i;
            if((bit&visited)!=0){
                cols[idx++]=i;
            }
        }
        
        //유일성 체크
        for(int y=0; y<H; y++){
            String str ="";
            for(int x : cols){
                str+=relations[y][x] + " ";
            }
            if(set.contains(str)) return false;
            set.add(str);
        }
        return true;
    }
}