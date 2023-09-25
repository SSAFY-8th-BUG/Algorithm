import java.util.*;

class Solution {
    static Set<Integer>[] sets;
    static int N,M, answer, number;
    public int solution(int N, int number) {
        answer = -1;
        this.N=N;
        this.number=number;
        if(N==number)return 1;
        sets = new Set[9];
        sets[1] = new HashSet<>();
        sets[1].add(N);
        
        
        for(int m=2; m<=8; m++){
            if(answer!=-1)break;
            M=m;
            sets[m] = new HashSet<>();
            sets[m].add(getNum(m));
            for(int i=1; i<=m-1; i++){
                int j = m-i;
                if(i>j)break;
                cal(i,j);
                if(sets[m].contains(number)){
                    answer=m;
                    break;
                }
            }
            //System.out.println(sets[m].toString());
        }
        return answer;
    }
    
    int getNum(int m){
        int ret=0;
        for(int i=0; i<m; i++){
            ret += Math.pow(10,i)*N;
        }
        return ret;
    }
    
    void cal(int i, int j){
        if(answer!=-1)return;
        Set<Integer> set = sets[M];
        int num;
        Iterator<Integer> it1 = sets[i].iterator();
        
        for(int n1 : sets[i]){
            for(int n2 : sets[j]){
                num=n1+n2;
                set.add(num);
                
                num=n1*n2;
                set.add(num);
                
                num=n1-n2;
                set.add(num);
                
                num=n2-n1;
                set.add(num);
                
                if(n2!=0){
                    num=n1/n2;
                    set.add(num);
                }
                
                if(n1!=0){
                    num=n2/n1;
                    set.add(num);
                }
            }
        }
    }
}